package com.cineinfo.v1.repository.admin.kmdb;

import com.cineinfo.v1.domain.admin.kmdb.KMDbMovieVods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KMDbMovieVodsRepository extends JpaRepository<KMDbMovieVods, Long> {

    List<KMDbMovieVods> findByKmdbMovieInfo_MovieId(String movieId);
}
