package com.cineinfo.v1.repository.admin.kmdb;

import com.cineinfo.v1.domain.admin.kmdb.KMDbMoviePlots;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KMDbMoviePlotsRepository extends JpaRepository<KMDbMoviePlots, Long> {
    Optional<KMDbMoviePlots> findByKmdbMovieInfo_MovieIdAndPlotTextContains(String movieId, String plotText);
    Optional<KMDbMoviePlots> findByKmdbMovieInfo_MovieIdAndPlotLang(String movieId, String plotLang);
}
