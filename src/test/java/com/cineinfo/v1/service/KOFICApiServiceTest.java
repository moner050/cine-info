package com.cineinfo.v1.service;

import com.cineinfo.v1.domain.kofic.KOFICComCode;
import com.cineinfo.v1.domain.kofic.KOFICDailyBoxOffice;
import com.cineinfo.v1.domain.kofic.constant.KOFICSummaryCd;
import com.cineinfo.v1.repository.kmdb.KMDbMovieInfoRepository;
import com.cineinfo.v1.repository.kofic.KOFICComCodeRepository;
import com.cineinfo.v1.repository.kofic.KOFICDailyBoxOfficeRepository;
import com.cineinfo.v1.repository.kofic.KOFICWeeklyBoxOfficeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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
    void saveComCode() {
        // given
        koficApiService.saveComCode(KOFICSummaryCd.COMPANY_PART_CD.getSummaryCd());

        // when
        List<KOFICComCode> KOFICComCodes = KOFICComCodeRepository.findBySummaryCd(KOFICSummaryCd.COMPANY_PART_CD.getSummaryCd());

        // then
        assertThat(KOFICComCodes).isNotNull();
    }

    @Test
    @DisplayName("일별 박스오피스 순위 저장 및 조회")
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
}