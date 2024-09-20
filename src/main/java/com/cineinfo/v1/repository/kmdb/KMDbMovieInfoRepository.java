package com.cineinfo.v1.repository.kmdb;

import com.cineinfo.v1.domain.kmdb.KMDbMovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface KMDbMovieInfoRepository extends JpaRepository<KMDbMovieInfo, String> {

    List<KMDbMovieInfo> findByTitleContains(String title);
    List<KMDbMovieInfo> findByGenreContains(String genre);
    List<KMDbMovieInfo> findByKeywordsContains(String keywords);
    Optional<KMDbMovieInfo> findByTitleContainsAndRepRlsDate(String title, LocalDate repRlsDate);
}
