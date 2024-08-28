package com.cineinfo.v1.repository.kofic;

import com.cineinfo.v1.domain.kofic.MovieCompanies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCompaniesRepository extends JpaRepository<MovieCompanies, String> {
}
