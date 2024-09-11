package com.cineinfo.v1.repository.kmdb;

import com.cineinfo.v1.domain.kmdb.KMDbMovieVods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KMDbMovieVodsRepository extends JpaRepository<KMDbMovieVods, Long> {

    List<KMDbMovieVods> findByKmdbMovieInfo_MovieId(String movieId);
}
