package com.cineinfo.v1.service;

import com.cineinfo.v1.domain.kofic.KOFICComCode;
import com.cineinfo.v1.domain.kofic.KOFICCompanyInfo;
import com.cineinfo.v1.domain.kofic.KOFICMovieInfo;
import com.cineinfo.v1.domain.kofic.constant.KOFICSummaryCd;
import com.cineinfo.v1.repository.kofic.KOFICComCodeRepository;
import com.cineinfo.v1.repository.kofic.KOFICCompanyInfoRepository;
import com.cineinfo.v1.repository.kofic.KOFICMovieInfoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@DisplayName("KOFIC API 서비스 연결 테스트")
class KOFICApiServiceTest {

    @Autowired
    KOFICApiService koficApiService;
    @Autowired
    KOFICMovieInfoRepository KOFICMovieInfoRepository;
    @Autowired
    KOFICComCodeRepository KOFICComCodeRepository;
    @Autowired
    KOFICCompanyInfoRepository KOFICCompanyInfoRepository;

    @Test
    @DisplayName("공통코드 저장")
    void saveComCode() {
        // given
        koficApiService.saveComCode(KOFICSummaryCd.COMPANY_PART_CD.getSummaryCd());

        // when
        List<KOFICComCode> KOFICComCodes = KOFICComCodeRepository.findBySummaryCd(KOFICSummaryCd.COMPANY_PART_CD.getSummaryCd());

        // then
        Assertions.assertThat(KOFICComCodes).isNotNull();
    }

    @Test
    @DisplayName("영화 리스트 저장")
    void saveMovieList() {
        // given
        koficApiService.saveMovieList("1", "2024");

        // when
        List<KOFICMovieInfo> all = KOFICMovieInfoRepository.findAll();

        // then
        Assertions.assertThat(all.size()).isEqualTo(100);
    }

    @Test
    @DisplayName("영화사 리스트 저장")
    void saveCompanyList() {
        // given
        koficApiService.saveCompanyList("1");

        // when
        List<KOFICCompanyInfo> all = KOFICCompanyInfoRepository.findAll();

        // then
        Assertions.assertThat(all.size()).isEqualTo(100);
    }

}