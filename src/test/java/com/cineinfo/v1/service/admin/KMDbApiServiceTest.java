package com.cineinfo.v1.service.admin;

import com.cineinfo.v1.domain.admin.kmdb.*;
import com.cineinfo.v1.repository.admin.kmdb.*;
import com.cineinfo.v1.service.admin.KMDbApiService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@SpringBootTest
public class KMDbApiServiceTest {

    @Autowired
    KMDbApiService kmdbApiService;
    @Autowired
    KMDbMovieInfoRepository kmdbMovieInfoRepository;
    @Autowired
    KMDbMoviePlotsRepository kmdbMoviePlotsRepository;
    @Autowired
    KMDbMoviePostersRepository kmdbMoviePostersRepository;
    @Autowired
    KMDbMovieStaffsRepository kmdbMovieStaffsRepository;
    @Autowired
    KMDbMovieStillsRepository kmdbMovieStillsRepository;
    @Autowired
    KMDbMovieVodsRepository kmdbMovieVodsRepository;

    @Test
    @DisplayName("영화 검색 및 전체 저장")
    @Rollback
    void saveAllKMDbMovieList() throws InterruptedException {
        // given

        // when
        String releaseDts = "20240101", releaseDte = "20241231";
        boolean chk = kmdbApiService.saveAllKMDbMovieList(releaseDts, releaseDte);

        LocalDate startDate = LocalDate.parse(releaseDts, DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate endDate = LocalDate.parse(releaseDte, DateTimeFormatter.ofPattern("yyyyMMdd"));

        // then
        assertThat(chk).isTrue();
        List<KMDbMovieInfo> savedMovieInfos = kmdbMovieInfoRepository.findAllByRepRlsDateBetween(startDate, endDate);

        assertThat(savedMovieInfos.size()).isEqualTo(515);
    }

    @Test
    @DisplayName("영화 검색 및 저장")
    @Transactional
    void saveKMDbMovieList() {
        // given

        // when
        int totalCount = kmdbApiService.saveKMDbMovieList("0", "3", "20230101", "20231231");

        // then
        List<KMDbMovieInfo> movieListAll = kmdbMovieInfoRepository.findAll();
        assertThat(totalCount).isEqualTo(653);

        for (KMDbMovieInfo kmDbMovieInfo : movieListAll) {
            log.info("saved movie id = " + kmDbMovieInfo.getMovieId());
        }

        KMDbMovieInfo movieInfo = kmdbMovieInfoRepository.findById("F05730")
                .orElseThrow(() -> new RuntimeException("존재하지 않는 아이디입니다."));

        KMDbMoviePlots moviePlot = kmdbMoviePlotsRepository.findByKmdbMovieInfo_MovieIdAndPlotLang(movieInfo.getMovieId(), "한국어")
                .orElseThrow(() -> new RuntimeException("줄거리 정보가 존재하지 않습니다."));

        KMDbMovieStaffs movieStaff = kmdbMovieStaffsRepository.findByKmdbMovieInfo_MovieIdAndStaffNm(movieInfo.getMovieId(), "비토리오 데 시카")
                .orElseThrow(() -> new RuntimeException("스태프 정보가 존재하지 않습니다."));

        List<KMDbMoviePosters> moviePosters = kmdbMoviePostersRepository.findByKmdbMovieInfo_MovieId(movieInfo.getMovieId());
        List<KMDbMovieStills> movieStills = kmdbMovieStillsRepository.findByKmdbMovieInfo_movieId(movieInfo.getMovieId());
        List<KMDbMovieVods> movieVods = kmdbMovieVodsRepository.findByKmdbMovieInfo_MovieId(movieInfo.getMovieId());

        log.info("repRatDate : " + movieInfo.getRepRateDate());
        log.info("repRlsDate : " + movieInfo.getRepRlsDate());
        log.info("regDate : " + movieInfo.getRegDate());
        log.info("modDate : " + movieInfo.getModDate());

        assertThat(movieInfo.getTitle()).isEqualTo(" 자전거 도둑");
        assertThat(movieInfo.getProdYear()).isEqualTo("1948");
        assertThat(movieInfo.getNation()).isEqualTo("이탈리아");
        assertThat(movieInfo.getPurpose()).isEqualTo("극장용");
        assertThat(movieInfo.getRepRateDate()).isEqualTo("2023-02-20");
        assertThat(movieInfo.getRepRlsDate()).isEqualTo("2023-04-26");
        assertThat(movieInfo.getRegDate()).isEqualTo("1900-01-01");
        assertThat(movieInfo.getModDate()).isEqualTo("2023-04-10");

        assertThat(movieInfo.getGenre()).contains("범죄");
        assertThat(movieInfo.getKeywords()).contains("자전거");
        assertThat(moviePlot.getPlotText()).contains("2차 대전");

        assertThat(movieStaff.getStaffRoleGroup()).isEqualTo("감독,각본");
        assertThat(movieStaff.getStaffRole()).isBlank();
        assertThat(movieStaff.getStaffEtc()).isBlank();

        assertThat(moviePosters.size()).isGreaterThan(1);
        assertThat(moviePosters.size()).isLessThan(3);

        assertThat(movieStills.size()).isGreaterThan(7);
        assertThat(movieStills.size()).isLessThan(9);

        assertThat(movieVods.size()).isGreaterThan(0);
        assertThat(movieVods.size()).isLessThan(2);
        assertThat(movieVods.get(0).getVodName()).isEqualTo("자전거 도둑 [40초예고편]");

    }

    @Test
    @DisplayName("영화 검색 및 저장 실패")
    void failSaveKMDbMovieList() {
        // given

        // when
        Throwable t = BDDAssertions.catchThrowable(() -> kmdbApiService.saveKMDbMovieList("-1", "3", "20230101", "20231231"));

        // then
        assertThat(t).isInstanceOf(HttpServerErrorException.class);
    }
}
