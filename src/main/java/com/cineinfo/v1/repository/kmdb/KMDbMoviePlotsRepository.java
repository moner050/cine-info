package com.cineinfo.v1.repository.kmdb;

import com.cineinfo.v1.domain.kmdb.KMDbMoviePlots;
import org.springframework.data.jpa.repository.JpaRepository;
public interface KMDbMoviePlotsRepository extends JpaRepository<KMDbMoviePlots, Long> {
}
