package com.cineinfo.v1.service;

import com.cineinfo.v1.domain.kofic.MovieInfo;
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

}