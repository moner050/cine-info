package com.cineinfo.v1.repository.kmdb;

import com.cineinfo.v1.domain.kmdb.KMDbMovieStills;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KMDbMovieStillsRepository extends JpaRepository<KMDbMovieStills, Long> {

    List<KMDbMovieStills> findByKmdbMovieInfo_movieId(String movieId);
}
