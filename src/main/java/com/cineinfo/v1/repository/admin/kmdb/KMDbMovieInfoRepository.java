package com.cineinfo.v1.repository.admin.kmdb;

import com.cineinfo.v1.domain.admin.kmdb.KMDbMovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface KMDbMovieInfoRepository extends JpaRepository<KMDbMovieInfo, String> {

    List<KMDbMovieInfo> findByTitleContains(String title);
    List<KMDbMovieInfo> findByGenreContains(String genre);
    List<KMDbMovieInfo> findByKeywordsContains(String keywords);
    List<KMDbMovieInfo> findAllByRepRlsDateBetween(LocalDate startDate, LocalDate endDate);
    Optional<KMDbMovieInfo> findByTitleContainsAndRepRlsDate(String title, LocalDate repRlsDate);
}
