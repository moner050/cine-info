package com.cineinfo.v1.repository.kofic;

import com.cineinfo.v1.domain.kofic.MovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieInfoRepository extends JpaRepository<MovieInfo, String> {
}
