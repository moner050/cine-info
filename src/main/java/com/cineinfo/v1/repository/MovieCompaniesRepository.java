package com.cineinfo.v1.repository;

import com.cineinfo.v1.domain.MovieCompanies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCompaniesRepository extends JpaRepository<MovieCompanies, String> {
}
