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
        List<DailyBoxOfficeListRes> dailyBoxOfficeList = searchKOFICDailyBoxOfficeRes.getBoxOfficeResult().getDailyBoxOfficeList();

        for (DailyBoxOfficeListRes dailyBoxOffice : dailyBoxOfficeList) {
            Optional<KMDbMovieInfo> savedMovieInfo = kmdbMovieInfoRepository
                    .findByTitleContainsAndRepRlsDate(dailyBoxOffice.getMovieNm(), LocalDate.parse(dailyBoxOffice.getOpenDt(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

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
        List<WeeklyBoxOfficeListRes> weeklyBoxOfficeList = searchKOFICWeeklyBoxOfficeRes.getBoxOfficeResult().getWeeklyBoxOfficeList();

        // 조회 기간
        String[] showRange = searchKOFICWeeklyBoxOfficeRes.getBoxOfficeResult().getShowRange().split("~");
        String startDate = showRange[0], endDate = showRange[1];

        for (WeeklyBoxOfficeListRes weeklyBoxOffice : weeklyBoxOfficeList) {
            Optional<KMDbMovieInfo> savedMovieInfo =  kmdbMovieInfoRepository
                    .findByTitleContainsAndRepRlsDate(weeklyBoxOffice.getMovieNm(), LocalDate.parse(weeklyBoxOffice.getOpenDt(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

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
