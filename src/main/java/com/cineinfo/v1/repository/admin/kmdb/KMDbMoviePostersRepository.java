package com.cineinfo.v1.repository.admin.kmdb;

import com.cineinfo.v1.domain.admin.kmdb.KMDbMoviePosters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KMDbMoviePostersRepository extends JpaRepository<KMDbMoviePosters, Long> {

    List<KMDbMoviePosters> findByKmdbMovieInfo_MovieId(String movieId);
}
