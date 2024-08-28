package com.cineinfo.v1.repository.kofic;

import com.cineinfo.v1.domain.kofic.MoviePeople;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviePeopleRepository extends JpaRepository<MoviePeople, String> {
}
