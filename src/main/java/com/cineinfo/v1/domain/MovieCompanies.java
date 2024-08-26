package com.cineinfo.v1.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity(name = "movie_companies")
@Getter
public class MovieCompanies {

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_cd")
    private MovieInfo movieInfo;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "company_cd")
    private CompanyInfo companyInfo;
}
