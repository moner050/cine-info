package com.cineinfo.v1.service.admin;

import com.cineinfo.v1.client.KOFICClient;
import com.cineinfo.v1.domain.admin.kmdb.KMDbMovieInfo;
import com.cineinfo.v1.domain.admin.kofic.KOFICComCode;
import com.cineinfo.v1.domain.admin.kofic.KOFICDailyBoxOffice;
import com.cineinfo.v1.domain.admin.kofic.KOFICWeeklyBoxOffice;
import com.cineinfo.v1.dto.admin.MovieStatDto;
import com.cineinfo.v1.dto.admin.kofic.response.SearchKOFICCodeListRes;
import com.cineinfo.v1.dto.admin.kofic.response.SearchKOFICDailyBoxOfficeRes;
import com.cineinfo.v1.dto.admin.kofic.response.SearchKOFICWeeklyBoxOfficeRes;
import com.cineinfo.v1.dto.admin.kofic.response.comcode.CodesRes;
import com.cineinfo.v1.dto.admin.kofic.response.dailyboxoffice.DailyBoxOfficeListRes;
import com.cineinfo.v1.dto.admin.kofic.response.weeklyboxoffice.WeeklyBoxOfficeListRes;
import com.cineinfo.v1.repository.admin.kmdb.KMDbMovieInfoRepository;
import com.cineinfo.v1.repository.admin.kofic.KOFICComCodeRepository;
import com.cineinfo.v1.repository.admin.kofic.KOFICDailyBoxOfficeRepository;
import com.cineinfo.v1.repository.admin.kofic.KOFICWeeklyBoxOfficeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class KOFICApiService {

    private final KOFICClient koficClient;
    private final KOFICComCodeRepository koficComCodeRepository;
    private final KOFICDailyBoxOfficeRepository koficDailyBoxOfficeRepository;
    private final KOFICWeeklyBoxOfficeRepository koficWeeklyBoxOfficeRepository;

    private final KMDbMovieInfoRepository kmdbMovieInfoRepository;

    private final ThreadPoolTaskExecutor taskExecutor;

    // 공통 코드 저장
    @Transactional
    public boolean saveComCode(String summary) {
        int count = 0;
        SearchKOFICCodeListRes searchCodeList = koficClient.searchCodeList(summary);

        // 에러 코드 반환시 출력 후 false 리턴
        if(searchCodeList.getFaultInfo() != null) {
            log.error(searchCodeList.getFaultInfo().getMessage());
            return false;
        }

        // 결과 값이 아예 존재하지 않으면 false 리턴
        if(searchCodeList.getCodes().isEmpty()) {
            log.error("ComCode 결과값이 존재하지 않습니다.");
            return false;
        }

        List<CodesRes> codes = searchCodeList.getCodes();

        for (CodesRes code : codes) {
            KOFICComCode entity = code.toEntity(summary);

            if (!koficComCodeRepository.existsById(entity.getFullCd())) {
                koficComCodeRepository.save(entity);
                count++;
            }
        }

        log.info(count + " 개 저장 완료.");
        return true;
    }

    // 일간 박스오피스 순위 저장 (기간 지정)
    @Transactional
    public boolean saveAllDailyBoxOffice(String startDate, String endDate, String repNationCd) {
        if(!checkDate(startDate) || !checkDate(endDate)) {
            throw new RuntimeException("날짜 형식이 맞지 않습니다.");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        try {
            Calendar cal = Calendar.getInstance();
            Date sDate = sdf.parse(startDate);
            Date eDate = sdf.parse(endDate);

            cal.setTime(sDate);

            // 날짜 일수 차이 구하기
            long diff = (eDate.getTime() - sDate.getTime()) / 86400000L;

            for (int i = 0, count = 0; i <= diff; i++) {
                cal.add(Calendar.DAY_OF_MONTH, count);
                String newTargetDate = sdf.format(cal.getTime());

                log.info(newTargetDate + " 일간 박스오피스 검색 시작.");

                if(!saveDailyBoxOffice(newTargetDate, repNationCd)) break;

                count = 1;
            }

        }
        catch (ParseException e) {
            throw new RuntimeException("날짜 형식에 맞지 않습니다. {}", e);
        }

        return true;
    }

    // 일간 박스오피스 순위 저장
    @Transactional
    public boolean saveDailyBoxOffice(String targetDt, String repNationCd) {
        SearchKOFICDailyBoxOfficeRes searchKOFICDailyBoxOfficeRes = koficClient.searchDailyBoxOffice(targetDt, repNationCd);

        // 만약 응답값에 에러코드가 있으면 false 반환
        if(searchKOFICDailyBoxOfficeRes.getFaultInfo() != null) {
            log.error(searchKOFICDailyBoxOfficeRes.getFaultInfo().getMessage());
            return false;
        }

        List<DailyBoxOfficeListRes> dailyBoxOfficeList = searchKOFICDailyBoxOfficeRes.getBoxOfficeResult().getDailyBoxOfficeList();

        if(dailyBoxOfficeList.isEmpty()) {
            log.info("일간 박스오피스 조회 값이 존재하지 않습니다.");
            return false;
        }

        CountDownLatch latch = new CountDownLatch(searchKOFICDailyBoxOfficeRes.getBoxOfficeResult().getDailyBoxOfficeList().size());

        for (DailyBoxOfficeListRes dailyBoxOffice : dailyBoxOfficeList) {
            taskExecutor.submit(() -> {
                try {
                    // 개봉일 추출
                    LocalDate openDt = LocalDate.parse(dailyBoxOffice.getOpenDt(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    // 기준일 변환
                    LocalDate parseTargetDate = LocalDate.parse(targetDt, DateTimeFormatter.ofPattern("yyyyMMdd"));

                    // 만약 해당 일별 박스오피스 데이터가 있으면 건너뛰기
                    if(koficDailyBoxOfficeRepository
                            .existsByKoficDailyBoxOfficeId_MovieNmAndKoficDailyBoxOfficeId_OpenDtAndKoficDailyBoxOfficeId_RepNationCdAndKoficDailyBoxOfficeId_TargetDate(dailyBoxOffice.getMovieNm().trim(), openDt, repNationCd, parseTargetDate)) {
                        log.info(targetDt + "/" + dailyBoxOffice.getOpenDt() + " 일자의 " + dailyBoxOffice.getMovieNm() + " " + repNationCd + " 박스오피스 순위 데이터가 이미 존재합니다.");
                    }
                    else {
                        Optional<KMDbMovieInfo> savedMovieInfo = kmdbMovieInfoRepository
                                .findByTitleContainsAndRepRlsDate(dailyBoxOffice.getMovieNm(), openDt);

                        if(savedMovieInfo.isEmpty()) {
                            log.info(dailyBoxOffice.getMovieNm() + " 영화 데이터 존재하지 않음.");
                            koficDailyBoxOfficeRepository.save(dailyBoxOffice.toEntity(repNationCd, targetDt));
                        }
                        else {
                            log.info(dailyBoxOffice.getMovieNm() + " 일간 박스오피스 저장 완료");
                            KOFICDailyBoxOffice savedDailyBoxOffice = koficDailyBoxOfficeRepository.save(dailyBoxOffice.toEntity(repNationCd, targetDt, savedMovieInfo.get()));

                            // 만약 저장된 영화정보 매출액 데이터의 날짜가 박스오피스 기준일자보다 이전이면 업데이트 처리
                            if(savedMovieInfo.get().getStatDate() == null || savedMovieInfo.get().getStatDate().isBefore(savedDailyBoxOffice.getKoficDailyBoxOfficeId().getTargetDate())) {
                                MovieStatDto movieStat = MovieStatDto.of(parseTargetDate, savedDailyBoxOffice.getAudiAcc(), savedDailyBoxOffice.getSalesAcc(), "kobis");
                                updateMovieStat(savedMovieInfo.get(), movieStat);
                                log.info(dailyBoxOffice.getMovieNm() + " 영화 매출액 및 관객수 데이터 갱신 완료.");
                            }
                        }
                        koficDailyBoxOfficeRepository.flush();
                        kmdbMovieInfoRepository.flush();
                    }
                }
                finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        }
        catch (InterruptedException e) {
            throw new RuntimeException("스레드가 블로킹 되었습니다.");
        }

        return true;
    }

    // 주간 박스오피스 순위 저장 (기간 지정)
    @Transactional
    public boolean saveAllWeeklyBoxOffice(String startDate, String endDate, String repNationCd, String weekGb) {
        if(!checkDate(startDate) || !checkDate(endDate)) {
            throw new RuntimeException("날짜 형식이 맞지 않습니다.");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        try {
            Calendar cal = Calendar.getInstance();
            Date sDate = sdf.parse(startDate);
            Date eDate = sdf.parse(endDate);

            cal.setTime(sDate);

            // 날짜 일수 차이 구하기
            long diff = (eDate.getTime() - sDate.getTime()) / 86400000L;

            for (int i = 0; i <= diff; ) {
                String newTargetDate = sdf.format(cal.getTime());

                log.info(newTargetDate + " 주간 박스오피스 검색 시작.");

                String resultEndDate = saveWeeklyBoxOffice(newTargetDate, repNationCd, weekGb);

                if(!resultEndDate.isBlank()) {
                    // endDate 결과값에 따라 i 및 날짜 변환
                    Date rstEndDate = sdf.parse(resultEndDate);
                    Date beforeTime = cal.getTime();
                    int nextWeekTime = Integer.parseInt(String.valueOf((rstEndDate.getTime() - beforeTime.getTime()) / 86400000L)) + 1;

                    cal.add(Calendar.DAY_OF_MONTH, nextWeekTime);
                    i = nextWeekTime;
                }
                else {
                    break;
                }
            }

        }
        catch (ParseException e) {
            throw new RuntimeException("날짜 형식에 맞지 않습니다. {}", e);
        }

        return true;
    }


    // 주간 박스오피스 순위 저장
    @Transactional
    public String saveWeeklyBoxOffice(String targetDt, String repNationCd, String weekGb) {
        SearchKOFICWeeklyBoxOfficeRes searchKOFICWeeklyBoxOfficeRes = koficClient.searchWeeklyBoxOffice(targetDt, repNationCd, weekGb);

        // 만약 응답값에 에러코드가 있으면 false 리턴
        if(searchKOFICWeeklyBoxOfficeRes.getFaultInfo() != null) {
            log.error(searchKOFICWeeklyBoxOfficeRes.getFaultInfo().getMessage());
            return searchKOFICWeeklyBoxOfficeRes.getFaultInfo().getMessage();
        }
        List<WeeklyBoxOfficeListRes> weeklyBoxOfficeList = searchKOFICWeeklyBoxOfficeRes.getBoxOfficeResult().getWeeklyBoxOfficeList();

        if(weeklyBoxOfficeList.isEmpty()) {
            log.info("주간 박스오피스 조회 값이 존재하지 않습니다.");
            return "";
        }

        // 조회 기간
        String[] showRange = searchKOFICWeeklyBoxOfficeRes.getBoxOfficeResult().getShowRange().split("~");
        String startDate = showRange[0], endDate = showRange[1];

        CountDownLatch latch = new CountDownLatch(searchKOFICWeeklyBoxOfficeRes.getBoxOfficeResult().getWeeklyBoxOfficeList().size());

        for (WeeklyBoxOfficeListRes weeklyBoxOffice : weeklyBoxOfficeList) {
            taskExecutor.submit(() -> {
                try {
                    // 개봉일 추출
                    LocalDate openDt = LocalDate.parse(weeklyBoxOffice.getOpenDt(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate convertStartDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd"));

                    // 해당 주간 박스오피스 데이터가 있으면 건너뛰기
                    if(koficWeeklyBoxOfficeRepository
                            .existsByKoficWeeklyBoxOfficeId_MovieNmAndKoficWeeklyBoxOfficeId_OpenDtAndKoficWeeklyBoxOfficeId_RepNationCdAndKoficWeeklyBoxOfficeId_StartDateRange(weeklyBoxOffice.getMovieNm(), openDt, repNationCd, convertStartDate)) {
                        log.info(startDate + " 일자의 " + weeklyBoxOffice.getMovieNm() + " 주간 박스오피스 순위 데이터가 이미 존재합니다.");
                    }
                    else {
                        Optional<KMDbMovieInfo> savedMovieInfo =  kmdbMovieInfoRepository
                                .findByTitleContainsAndRepRlsDate(weeklyBoxOffice.getMovieNm(), openDt);

                        if(savedMovieInfo.isEmpty()) {
                            log.info(weeklyBoxOffice.getMovieNm() + " 영화 데이터 존재하지 않음.");
                            koficWeeklyBoxOfficeRepository.save(weeklyBoxOffice.toEntity(repNationCd, startDate, endDate));
                        }
                        else {
                            log.info(weeklyBoxOffice.getMovieNm() + " 주간 박스오피스 순위 저장");
                            KOFICWeeklyBoxOffice savedWeeklyBoxOffice = koficWeeklyBoxOfficeRepository.save(weeklyBoxOffice.toEntity(repNationCd, startDate, endDate, savedMovieInfo.get()));

                            // 만약 저장된 영화정보 매출액 데이터의 날짜가 박스오피스 기준일자보다 이전이면 업데이트 처리
                            if(savedMovieInfo.get().getStatDate() == null || savedMovieInfo.get().getStatDate().isBefore(savedWeeklyBoxOffice.getKoficWeeklyBoxOfficeId().getEndDateRange())) {
                                MovieStatDto movieStat = MovieStatDto.of(convertStartDate, savedWeeklyBoxOffice.getAudiAcc(), savedWeeklyBoxOffice.getSalesAcc(), "kobis");
                                updateMovieStat(savedMovieInfo.get(), movieStat);
                                log.info(weeklyBoxOffice.getMovieNm() + " 영화 매출액 및 관객수 데이터 갱신 완료.");
                            }
                        }
                        koficWeeklyBoxOfficeRepository.flush();
                        kmdbMovieInfoRepository.flush();
                    }
                }
                finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await();
        }
        catch (InterruptedException e) {
            throw new RuntimeException("스레드가 블로킹 되었습니다.");
        }

        return endDate;
    }

    // 영화 매출 및 관객 업데이트
    @Transactional
    public KMDbMovieInfo updateMovieStat(KMDbMovieInfo kmdbMovieInfo, MovieStatDto movieStatDto) {
        return kmdbMovieInfoRepository.save(KMDbMovieInfo.builder()
                .movieId(kmdbMovieInfo.getMovieId())
                .title(kmdbMovieInfo.getTitle())
                .titleEng(kmdbMovieInfo.getTitleEng())
                .titleOrg(kmdbMovieInfo.getTitleOrg())
                .prodYear(kmdbMovieInfo.getProdYear())
                .nation(kmdbMovieInfo.getNation())
                .company(kmdbMovieInfo.getCompany())
                .runtime(kmdbMovieInfo.getRuntime())
                .genre(kmdbMovieInfo.getGenre())
                .type(kmdbMovieInfo.getType())
                .purpose(kmdbMovieInfo.getPurpose())
                .episodes(kmdbMovieInfo.getEpisodes())
                .ratedYn(kmdbMovieInfo.getRatedYn())
                .repRateDate(kmdbMovieInfo.getRepRateDate())
                .repRlsDate(kmdbMovieInfo.getRepRlsDate())
                .keywords(kmdbMovieInfo.getKeywords())
                .salesAcc(movieStatDto.salesAcc())
                .audiAcc(movieStatDto.audiAcc())
                .statSource(movieStatDto.stat_source())
                .statDate(movieStatDto.statDate())
                .themeSong(kmdbMovieInfo.getThemeSong())
                .fLocation(kmdbMovieInfo.getFLocation())
                .awards1(kmdbMovieInfo.getAwards1())
                .awards2(kmdbMovieInfo.getAwards2())
                .regDate(kmdbMovieInfo.getRegDate())
                .modDate(kmdbMovieInfo.getModDate())
                .build());
    }


    public boolean checkDate(String date) {
        try {
            SimpleDateFormat check = new SimpleDateFormat("yyyyMMdd");
            check.setLenient(false);
            check.parse(date);
        }
        catch (ParseException | NullPointerException e) {
            log.error(date + " 는 날짜 형식에 맞지 않습니다. {}", e.getMessage());
            return false;
        }
        return true;
    }
}
