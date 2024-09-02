package com.cineinfo.v1.repository.kofic;

import com.cineinfo.v1.domain.kofic.KOFICMovieInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KOFICMovieInfoRepository extends JpaRepository<KOFICMovieInfo, String> {
}
