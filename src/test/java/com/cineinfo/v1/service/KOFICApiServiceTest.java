package com.cineinfo.v1.service;

import com.cineinfo.v1.domain.admin.kofic.KOFICComCode;
import com.cineinfo.v1.domain.admin.kofic.KOFICDailyBoxOffice;
import com.cineinfo.v1.domain.admin.kofic.KOFICWeeklyBoxOffice;
import com.cineinfo.v1.domain.admin.kofic.constant.KOFICSummaryCd;
import com.cineinfo.v1.repository.admin.kmdb.KMDbMovieInfoRepository;
import com.cineinfo.v1.repository.admin.kofic.KOFICComCodeRepository;
import com.cineinfo.v1.repository.admin.kofic.KOFICDailyBoxOfficeRepository;
import com.cineinfo.v1.repository.admin.kofic.KOFICWeeklyBoxOfficeRepository;
import com.cineinfo.v1.service.admin.KMDbApiService;
import com.cineinfo.v1.service.admin.KOFICApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@DisplayName("KOFIC API 서비스 연결 테스트")
class KOFICApiServiceTest {

    @Autowired
    KOFICApiService koficApiService;
    @Autowired
    KMDbApiService kmdbApiService;
    @Autowired
    KOFICComCodeRepository KOFICComCodeRepository;
    @Autowired
    KOFICDailyBoxOfficeRepository koficDailyBoxOfficeRepository;
    @Autowired
    KOFICWeeklyBoxOfficeRepository koficWeeklyBoxOfficeRepository;
    @Autowired
    KMDbMovieInfoRepository kmDbMovieInfoRepository;

    @Test
    @DisplayName("공통코드 저장")
    @Transactional
    void saveComCode() {
        // given
        koficApiService.saveComCode(KOFICSummaryCd.COMPANY_PART_CD.getSummaryCd());

        // when
        List<KOFICComCode> KOFICComCodes = KOFICComCodeRepository.findBySummaryCd(KOFICSummaryCd.COMPANY_PART_CD.getSummaryCd());

        // then
        assertThat(KOFICComCodes).isNotNull();
    }

    @Test
    @DisplayName("공동코드 저장 실패")
    @Transactional
    void falseSaveComeCode() {
        // given

        // when
        boolean b = koficApiService.saveComCode("FALSE SUMMARY");

        // then
        assertThat(b).isFalse();

    }

    @Test
    @DisplayName("일별 박스오피스 순위 저장 및 조회")
    @Transactional
    void saveDailyBoxOffice() {
        // given

        // when
        boolean dailyChkA = koficApiService.saveDailyBoxOffice("20240919", "");
        boolean dailyChkK = koficApiService.saveDailyBoxOffice("20240919", "K");
        boolean dailyChkF = koficApiService.saveDailyBoxOffice("20240919", "F");

        // then
        assertThat(dailyChkA).isTrue();
        assertThat(dailyChkK).isTrue();
        assertThat(dailyChkF).isTrue();

        List<KOFICDailyBoxOffice> a = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCd("A");
        List<KOFICDailyBoxOffice> k = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCd("K");
        List<KOFICDailyBoxOffice> f = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCd("F");

        assertThat(a.size()).isEqualTo(10);
        assertThat(k.size()).isEqualTo(10);
        assertThat(f.size()).isEqualTo(10);

        LocalDate date = LocalDate.parse("20240919", DateTimeFormatter.ofPattern("yyyyMMdd"));

        KOFICDailyBoxOffice aRank1 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndTargetDate("A", 1, date)
                .orElseThrow(() -> new RuntimeException("A1 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice aRank3 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndTargetDate("A", 3, date)
                .orElseThrow(() -> new RuntimeException("A3 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice aRank5 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndTargetDate("A", 5, date)
                .orElseThrow(() -> new RuntimeException("A5 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice kRank1 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndTargetDate("K", 1, date)
                .orElseThrow(() -> new RuntimeException("K1 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice kRank3 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndTargetDate("K", 3, date)
                .orElseThrow(() -> new RuntimeException("K3 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice kRank5 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndTargetDate("K", 5, date)
                .orElseThrow(() -> new RuntimeException("K5 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice fRank1 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndTargetDate("F", 1, date)
                .orElseThrow(() -> new RuntimeException("F1 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice fRank3 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndTargetDate("F", 3, date)
                .orElseThrow(() -> new RuntimeException("F3 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice fRank5 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndTargetDate("F", 5, date)
                .orElseThrow(() -> new RuntimeException("F5 검색된 박스오피스 순위가 없습니다."));

        assertThat(aRank1.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("베테랑2");
        assertThat(aRank3.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("비긴 어게인");
        assertThat(aRank5.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("정국: 아이 엠 스틸");

        assertThat(kRank1.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("베테랑2");
        assertThat(kRank3.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("임영웅│아임 히어로 더 스타디움");
        assertThat(kRank5.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("브레드이발소: 빵스타의 탄생");

        assertThat(fRank1.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("룩백");
        assertThat(fRank3.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("에이리언: 로물루스");
        assertThat(fRank5.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("트랩");
    }

    @Test
    @DisplayName("일별 박스오피스 순위 저장 실패")
    @Transactional
    void falseSaveDailyBoxOffice() {
        // given

        // when
        boolean repNation = koficApiService.saveDailyBoxOffice("20240916", "AKF");
        boolean targetDate = koficApiService.saveDailyBoxOffice("202409161616", "K");

        // then
        assertThat(repNation).isFalse();
        assertThat(targetDate).isFalse();
    }

    @Test
    @DisplayName("주간 박스오피스 순위 저장 및 검색")
    @Transactional
    void saveWeeklyBoxOffice() {
        // given

        // when
        boolean weeklyChkA = koficApiService.saveWeeklyBoxOffice("20240916", "", "0");
        boolean weeklyChkF = koficApiService.saveWeeklyBoxOffice("20240916", "K", "0");
        boolean weeklyChkK = koficApiService.saveWeeklyBoxOffice("20240916", "F", "0");

        // then
        assertThat(weeklyChkA).isTrue();
        assertThat(weeklyChkF).isTrue();
        assertThat(weeklyChkK).isTrue();

        List<KOFICWeeklyBoxOffice> a = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCd("A");
        List<KOFICWeeklyBoxOffice> k = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCd("K");
        List<KOFICWeeklyBoxOffice> f = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCd("F");

        assertThat(a.size()).isEqualTo(10);
        assertThat(k.size()).isEqualTo(10);
        assertThat(f.size()).isEqualTo(10);

        LocalDate date = LocalDate.parse("20240916", DateTimeFormatter.ofPattern("yyyyMMdd"));

        KOFICWeeklyBoxOffice aRank1 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndStartDateRange("A", 1, date)
                .orElseThrow(() -> new RuntimeException("A1 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice aRank3 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndStartDateRange("A", 3, date)
                .orElseThrow(() -> new RuntimeException("A3 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice aRank5 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndStartDateRange("A", 5, date)
                .orElseThrow(() -> new RuntimeException("A5 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice kRank1 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndStartDateRange("K", 1, date)
                .orElseThrow(() -> new RuntimeException("K1 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice kRank3 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndStartDateRange("K", 3, date)
                .orElseThrow(() -> new RuntimeException("K3 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice kRank5 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndStartDateRange("K", 5, date)
                .orElseThrow(() -> new RuntimeException("K5 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice fRank1 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndStartDateRange("F", 1, date)
                .orElseThrow(() -> new RuntimeException("F1 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice fRank3 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndStartDateRange("F", 3, date)
                .orElseThrow(() -> new RuntimeException("F3 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice fRank5 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndStartDateRange("F", 5, date)
                .orElseThrow(() -> new RuntimeException("F5 검색된 박스오피스 순위가 없습니다."));

        assertThat(aRank1.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("베테랑2");
        assertThat(aRank3.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("브레드이발소: 빵스타의 탄생");
        assertThat(aRank5.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("에이리언: 로물루스");

        assertThat(kRank1.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("베테랑2");
        assertThat(kRank3.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("브레드이발소: 빵스타의 탄생");
        assertThat(kRank5.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("임영웅│아임 히어로 더 스타디움");

        assertThat(fRank1.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("비긴 어게인");
        assertThat(fRank3.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("룩백");
        assertThat(fRank5.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("트랜스포머 ONE");
    }

    @Test
    @DisplayName("주간 박스오피스 순위 저장 실패")
    @Transactional
    void falseSaveWeeklyBoxOffice() {
        // given

        // when
        boolean repNation = koficApiService.saveWeeklyBoxOffice("20240916", "AKF", "0");
        boolean targetDate = koficApiService.saveWeeklyBoxOffice("202409161616", "K", "0");
        boolean weekGb = koficApiService.saveWeeklyBoxOffice("20240916", "K", "10000");

        // then
        assertThat(repNation).isFalse();
        assertThat(targetDate).isFalse();
        assertThat(weekGb).isFalse();
    }

}