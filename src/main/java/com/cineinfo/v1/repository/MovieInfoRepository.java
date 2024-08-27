package com.cineinfo.v1.repository;

import com.cineinfo.v1.domain.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieInfoRepository extends JpaRepository<MovieInfo, String> {
}
