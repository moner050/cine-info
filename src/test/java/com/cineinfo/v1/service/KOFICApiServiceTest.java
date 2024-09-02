package com.cineinfo.v1.service;

import com.cineinfo.v1.domain.kofic.ComCode;
import com.cineinfo.v1.domain.kofic.CompanyInfo;
import com.cineinfo.v1.domain.kofic.MovieInfo;
import com.cineinfo.v1.domain.kofic.constant.SummaryCd;
import com.cineinfo.v1.repository.kofic.ComCodeRepository;
import com.cineinfo.v1.repository.kofic.CompanyInfoRepository;
import com.cineinfo.v1.repository.kofic.MovieInfoRepository;
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
    MovieInfoRepository movieInfoRepository;
    @Autowired
    ComCodeRepository comCodeRepository;
    @Autowired
    CompanyInfoRepository companyInfoRepository;

    @Test
    @DisplayName("공통코드 저장")
    void saveComCode() {
        // given
        koficApiService.saveComCode(SummaryCd.COMPANY_PART_CD.getSummaryCd());

        // when
        List<ComCode> comCodes = comCodeRepository.findBySummaryCd(SummaryCd.COMPANY_PART_CD.getSummaryCd());

        // then
        Assertions.assertThat(comCodes).isNotNull();
    }

    @Test
    @DisplayName("영화 리스트 저장")
    void saveMovieList() {
        // given
        koficApiService.saveMovieList("1", "2024");

        // when
        List<MovieInfo> all = movieInfoRepository.findAll();

        // then
        Assertions.assertThat(all.size()).isEqualTo(100);
    }

    @Test
    @DisplayName("영화사 리스트 저장")
    void saveCompanyList() {
        // given
        koficApiService.saveCompanyList("1");

        // when
        List<CompanyInfo> all = companyInfoRepository.findAll();

        // then
        Assertions.assertThat(all.size()).isEqualTo(100);
    }

}