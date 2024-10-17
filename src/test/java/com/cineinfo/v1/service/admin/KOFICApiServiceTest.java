package com.cineinfo.v1.service.admin;

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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("KOFIC API 서비스 연결 테스트")
@Transactional
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

        LocalDate date = LocalDate.parse("20240919", DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<KOFICDailyBoxOffice> a = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndKoficDailyBoxOfficeId_TargetDate("A", date);
        List<KOFICDailyBoxOffice> k = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndKoficDailyBoxOfficeId_TargetDate("K", date);
        List<KOFICDailyBoxOffice> f = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndKoficDailyBoxOfficeId_TargetDate("F", date);

        assertThat(a.size()).isEqualTo(10);
        assertThat(k.size()).isEqualTo(10);
        assertThat(f.size()).isEqualTo(10);


        KOFICDailyBoxOffice aRank1 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("A", 1, date)
                .orElseThrow(() -> new RuntimeException("A1 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice aRank3 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("A", 3, date)
                .orElseThrow(() -> new RuntimeException("A3 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice aRank5 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("A", 5, date)
                .orElseThrow(() -> new RuntimeException("A5 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice kRank1 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("K", 1, date)
                .orElseThrow(() -> new RuntimeException("K1 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice kRank3 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("K", 3, date)
                .orElseThrow(() -> new RuntimeException("K3 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice kRank5 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("K", 5, date)
                .orElseThrow(() -> new RuntimeException("K5 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice fRank1 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("F", 1, date)
                .orElseThrow(() -> new RuntimeException("F1 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice fRank3 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("F", 3, date)
                .orElseThrow(() -> new RuntimeException("F3 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice fRank5 = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("F", 5, date)
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
        String weeklyChkA = koficApiService.saveWeeklyBoxOffice("20240916", "", "0");
        String weeklyChkF = koficApiService.saveWeeklyBoxOffice("20240916", "K", "0");
        String weeklyChkK = koficApiService.saveWeeklyBoxOffice("20240916", "F", "0");

        // then
        assertThat(weeklyChkA).isEqualTo("20240922");
        assertThat(weeklyChkF).isEqualTo("20240922");
        assertThat(weeklyChkK).isEqualTo("20240922");

        LocalDate date = LocalDate.parse("20240916", DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<KOFICWeeklyBoxOffice> a = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndKoficWeeklyBoxOfficeId_StartDateRange("A", date);
        List<KOFICWeeklyBoxOffice> k = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndKoficWeeklyBoxOfficeId_StartDateRange("K", date);
        List<KOFICWeeklyBoxOffice> f = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndKoficWeeklyBoxOfficeId_StartDateRange("F", date);

        assertThat(a.size()).isEqualTo(10);
        assertThat(k.size()).isEqualTo(10);
        assertThat(f.size()).isEqualTo(10);

        KOFICWeeklyBoxOffice aRank1 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("A", 1, date)
                .orElseThrow(() -> new RuntimeException("A1 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice aRank3 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("A", 3, date)
                .orElseThrow(() -> new RuntimeException("A3 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice aRank5 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("A", 5, date)
                .orElseThrow(() -> new RuntimeException("A5 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice kRank1 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("K", 1, date)
                .orElseThrow(() -> new RuntimeException("K1 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice kRank3 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("K", 3, date)
                .orElseThrow(() -> new RuntimeException("K3 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice kRank5 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("K", 5, date)
                .orElseThrow(() -> new RuntimeException("K5 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice fRank1 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("F", 1, date)
                .orElseThrow(() -> new RuntimeException("F1 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice fRank3 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("F", 3, date)
                .orElseThrow(() -> new RuntimeException("F3 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice fRank5 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("F", 5, date)
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
        String repNation = koficApiService.saveWeeklyBoxOffice("20240916", "AKF", "0");
        String targetDate = koficApiService.saveWeeklyBoxOffice("202409161616", "K", "0");
        String weekGb = koficApiService.saveWeeklyBoxOffice("20240916", "K", "10000");

        // then
        assertThat(repNation).isEqualTo("국적구분 조건이 올바르지 않습니다.(F:외국영화, K:한국영화)[parameterName=repNationCd,parameterValue=AKF]");
        assertThat(targetDate).isEqualTo("날싸형식에 맞게 입력하십시요.(YYYYMMDD)[parameterName=targetDt,parameterValue=202409161616]");
        assertThat(weekGb).isEqualTo("주간/주말 조건이 올바르지 않습니다.(0:주간,1:주말,2:주중)[parameterName=weekGb,parameterValue=10000]");
    }

    @Test
    @DisplayName("일간 박스오피스 기간 지정 전체 저장 성공")
    @Transactional
    void saveAllDailyBoxOffice() {
        // given
        String startDate = "20241001";
        String endDate = "20241003";

        // when
        boolean aDaily = koficApiService.saveAllDailyBoxOffice(startDate, endDate, "");
        boolean kDaily = koficApiService.saveAllDailyBoxOffice(startDate, endDate, "K");
        boolean fDaily = koficApiService.saveAllDailyBoxOffice(startDate, endDate, "F");

        // then
        assertThat(aDaily).isTrue();
        assertThat(kDaily).isTrue();
        assertThat(fDaily).isTrue();

        LocalDate sDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate eDate = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<KOFICDailyBoxOffice> a = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndKoficDailyBoxOfficeId_TargetDateBetween("A", sDate, eDate);
        List<KOFICDailyBoxOffice> k = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndKoficDailyBoxOfficeId_TargetDateBetween("K", sDate, eDate);
        List<KOFICDailyBoxOffice> f = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndKoficDailyBoxOfficeId_TargetDateBetween("F", sDate, eDate);

        assertThat(a.size()).isEqualTo(30);
        assertThat(k.size()).isEqualTo(30);
        assertThat(f.size()).isEqualTo(30);

        KOFICDailyBoxOffice aRank1s = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("A", 1, sDate)
                .orElseThrow(() -> new RuntimeException("A1 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice aRank3s = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("A", 3, sDate)
                .orElseThrow(() -> new RuntimeException("A3 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice aRank5s = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("A", 5, sDate)
                .orElseThrow(() -> new RuntimeException("A5 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice kRank1s = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("K", 1, sDate)
                .orElseThrow(() -> new RuntimeException("K1 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice kRank3s = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("K", 3, sDate)
                .orElseThrow(() -> new RuntimeException("K3 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice kRank5s = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("K", 5, sDate)
                .orElseThrow(() -> new RuntimeException("K5 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice fRank1s = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("F", 1, sDate)
                .orElseThrow(() -> new RuntimeException("F1 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice fRank3s = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("F", 3, sDate)
                .orElseThrow(() -> new RuntimeException("F3 검색된 박스오피스 순위가 없습니다."));
        KOFICDailyBoxOffice fRank5s = koficDailyBoxOfficeRepository.findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate("F", 5, sDate)
                .orElseThrow(() -> new RuntimeException("F5 검색된 박스오피스 순위가 없습니다."));

        assertThat(aRank1s.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("조커: 폴리 아 되");
        assertThat(aRank3s.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("와일드 로봇");
        assertThat(aRank5s.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("트랜스포머 ONE");

        assertThat(kRank1s.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("베테랑2");
        assertThat(kRank3s.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("사랑의 하츄핑");
        assertThat(kRank5s.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("임영웅│아임 히어로 더 스타디움");

        assertThat(fRank1s.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("조커: 폴리 아 되");
        assertThat(fRank3s.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("트랜스포머 ONE");
        assertThat(fRank5s.getKoficDailyBoxOfficeId().getMovieNm()).isEqualTo("비긴 어게인");
    }

    @Test
    @DisplayName("주간 박스오피스 기간 지정 전체 저장 성공")
    @Transactional
    void saveAllWeeklyBoxOffice() {
        // given
        String startDate = "20240930";
        String endDate = "20241015";

        LocalDate sDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd"));

        // when
        boolean aWeekly = koficApiService.saveAllWeeklyBoxOffice(startDate, endDate, "", "0");
        boolean kWeekly = koficApiService.saveAllWeeklyBoxOffice(startDate, endDate, "K", "0");
        boolean fWeekly = koficApiService.saveAllWeeklyBoxOffice(startDate, endDate, "F", "0");

        // then
        assertThat(aWeekly).isTrue();
        assertThat(kWeekly).isTrue();
        assertThat(fWeekly).isTrue();

        List<KOFICWeeklyBoxOffice> aWeeklyList = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCd("A");
        List<KOFICWeeklyBoxOffice> kWeeklyList = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCd("K");
        List<KOFICWeeklyBoxOffice> fWeeklyList = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCd("F");

        assertThat(aWeeklyList.size()).isEqualTo(20);
        assertThat(kWeeklyList.size()).isEqualTo(20);
        assertThat(fWeeklyList.size()).isEqualTo(20);

        KOFICWeeklyBoxOffice aRank1 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("A", 1, sDate)
                .orElseThrow(() -> new RuntimeException("A1 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice aRank3 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("A", 3, sDate)
                .orElseThrow(() -> new RuntimeException("A3 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice aRank5 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("A", 5, sDate)
                .orElseThrow(() -> new RuntimeException("A5 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice kRank1 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("K", 1, sDate)
                .orElseThrow(() -> new RuntimeException("K1 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice kRank3 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("K", 3, sDate)
                .orElseThrow(() -> new RuntimeException("K3 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice kRank5 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("K", 5, sDate)
                .orElseThrow(() -> new RuntimeException("K5 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice fRank1 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("F", 1, sDate)
                .orElseThrow(() -> new RuntimeException("F1 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice fRank3 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("F", 3, sDate)
                .orElseThrow(() -> new RuntimeException("F3 검색된 박스오피스 순위가 없습니다."));
        KOFICWeeklyBoxOffice fRank5 = koficWeeklyBoxOfficeRepository.findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndKoficWeeklyBoxOfficeId_StartDateRange("F", 5, sDate)
                .orElseThrow(() -> new RuntimeException("F5 검색된 박스오피스 순위가 없습니다."));

        assertThat(aRank1.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("베테랑2");
        assertThat(aRank3.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("대도시의 사랑법");
        assertThat(aRank5.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("트랜스포머 ONE");

        assertThat(kRank1.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("베테랑2");
        assertThat(kRank3.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("사랑의 하츄핑");
        assertThat(kRank5.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("임영웅│아임 히어로 더 스타디움");

        assertThat(fRank1.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("조커: 폴리 아 되");
        assertThat(fRank3.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("트랜스포머 ONE");
        assertThat(fRank5.getKoficWeeklyBoxOfficeId().getMovieNm()).isEqualTo("비긴 어게인");
    }

}