package com.cineinfo.v1.repository;

import com.cineinfo.v1.domain.MoviePeople;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviePeopleRepository extends JpaRepository<MoviePeople, String> {
}
