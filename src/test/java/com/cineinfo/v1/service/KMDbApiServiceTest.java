package com.cineinfo.v1.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KMDbApiServiceTest {

    @Autowired
    KMDbApiService kmdbApiService;

    @Test
    @DisplayName("영화 검색")
    void searchKMDbMovieList() {
        // given
        boolean b = kmdbApiService.searchKMDbMovieList("0", "20230101", "20231231");

        // when


        // then
        Assertions.assertThat(b).isTrue();

    }
}
