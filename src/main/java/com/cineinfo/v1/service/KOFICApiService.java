package com.cineinfo.v1.service;

import com.cineinfo.v1.client.KOFICClient;
import com.cineinfo.v1.domain.kmdb.KMDbMovieInfo;
import com.cineinfo.v1.domain.kofic.KOFICComCode;
import com.cineinfo.v1.dto.kofic.response.SearchKOFICCodeListRes;
import com.cineinfo.v1.dto.kofic.response.SearchKOFICDailyBoxOfficeRes;
import com.cineinfo.v1.dto.kofic.response.SearchKOFICWeeklyBoxOfficeRes;
import com.cineinfo.v1.dto.kofic.response.comcode.CodesRes;
import com.cineinfo.v1.dto.kofic.response.dailyboxoffice.DailyBoxOfficeListRes;
import com.cineinfo.v1.dto.kofic.response.weeklyboxoffice.WeeklyBoxOfficeListRes;
import com.cineinfo.v1.repository.kmdb.KMDbMovieInfoRepository;
import com.cineinfo.v1.repository.kofic.KOFICComCodeRepository;
import com.cineinfo.v1.repository.kofic.KOFICDailyBoxOfficeRepository;
import com.cineinfo.v1.repository.kofic.KOFICWeeklyBoxOfficeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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

        for (DailyBoxOfficeListRes dailyBoxOffice : dailyBoxOfficeList) {
            // 개봉일 추출
            LocalDate openDt = LocalDate.parse(dailyBoxOffice.getOpenDt(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // 만약 해당 일별 박스오피스 데이터가 있으면 건너뛰기
            if(koficDailyBoxOfficeRepository
                    .existsByKoficDailyBoxOfficeId_MovieNmAndKoficDailyBoxOfficeId_OpenDtAndKoficDailyBoxOfficeId_RepNationCd(dailyBoxOffice.getMovieNm(), openDt, repNationCd)) {
                continue;
            }

            Optional<KMDbMovieInfo> savedMovieInfo = kmdbMovieInfoRepository
                    .findByTitleContainsAndRepRlsDate(dailyBoxOffice.getMovieNm(), openDt);

            if(savedMovieInfo.isEmpty()) {
                log.info(dailyBoxOffice.getMovieNm() + " 영화 데이터 존재하지 않음.");
                koficDailyBoxOfficeRepository.save(dailyBoxOffice.toEntity(repNationCd, targetDt));
            }
            else {
                koficDailyBoxOfficeRepository.save(dailyBoxOffice.toEntity(repNationCd, targetDt, savedMovieInfo.get()));
            }
        }

        return true;
    }

    // 주간 박스오피스 순위 저장
    @Transactional
    public boolean saveWeeklyBoxOffice(String targetDt, String repNationCd, String weekGb) {
        SearchKOFICWeeklyBoxOfficeRes searchKOFICWeeklyBoxOfficeRes = koficClient.searchWeeklyBoxOffice(targetDt, repNationCd, weekGb);

        // 만약 응답값에 에러코드가 있으면 false 리턴
        if(searchKOFICWeeklyBoxOfficeRes.getFaultInfo() != null) {
            log.error(searchKOFICWeeklyBoxOfficeRes.getFaultInfo().getMessage());
            return false;
        }
        List<WeeklyBoxOfficeListRes> weeklyBoxOfficeList = searchKOFICWeeklyBoxOfficeRes.getBoxOfficeResult().getWeeklyBoxOfficeList();

        // 조회 기간
        String[] showRange = searchKOFICWeeklyBoxOfficeRes.getBoxOfficeResult().getShowRange().split("~");
        String startDate = showRange[0], endDate = showRange[1];

        for (WeeklyBoxOfficeListRes weeklyBoxOffice : weeklyBoxOfficeList) {
            // 개봉일 추출
            LocalDate openDt = LocalDate.parse(weeklyBoxOffice.getOpenDt(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate convertStartDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd"));

            // 해당 주간 박스오피스 데이터가 있으면 건너뛰기
            if(koficWeeklyBoxOfficeRepository
                    .existsByKoficWeeklyBoxOfficeId_MovieNmAndKoficWeeklyBoxOfficeId_OpenDtAndKoficWeeklyBoxOfficeId_RepNationCdAndStartDateRange(weeklyBoxOffice.getMovieNm(), openDt, repNationCd, convertStartDate)) {
                continue;
            }

            Optional<KMDbMovieInfo> savedMovieInfo =  kmdbMovieInfoRepository
                    .findByTitleContainsAndRepRlsDate(weeklyBoxOffice.getMovieNm(), openDt);

            if(savedMovieInfo.isEmpty()) {
                log.info(weeklyBoxOffice.getMovieNm() + " 영화 데이터 존재하지 않음.");
                koficWeeklyBoxOfficeRepository.save(weeklyBoxOffice.toEntity(repNationCd, startDate, endDate));
            }
            else {
                koficWeeklyBoxOfficeRepository.save(weeklyBoxOffice.toEntity(repNationCd, startDate, endDate, savedMovieInfo.get()));
            }
        }

        return true;
    }

}
