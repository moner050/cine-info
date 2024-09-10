package com.cineinfo.v1.repository.kmdb;

import com.cineinfo.v1.domain.kmdb.KMDbMovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KMDbMovieInfoRepository extends JpaRepository<KMDbMovieInfo, String> {
}
