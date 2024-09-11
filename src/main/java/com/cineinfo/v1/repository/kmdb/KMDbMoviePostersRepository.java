package com.cineinfo.v1.repository.kmdb;

import com.cineinfo.v1.domain.kmdb.KMDbMoviePosters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KMDbMoviePostersRepository extends JpaRepository<KMDbMoviePosters, Long> {

    List<KMDbMoviePosters> findByKmdbMovieInfo_MovieId(String movieId);
}
