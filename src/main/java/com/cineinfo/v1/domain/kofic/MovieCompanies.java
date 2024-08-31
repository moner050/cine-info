package com.cineinfo.v1.domain.kofic;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("영화의 영화사 정보")
@Entity(name = "movie_companies")
public class MovieCompanies {

    @EmbeddedId
    private MovieCompaniesKey id;

    @ManyToOne(optional = false)
    @MapsId("movieCd")
    @JoinColumn(name = "movie_cd")
    private MovieInfo movieInfo;

    @ManyToOne(optional = false)
    @MapsId("companyCd")
    @JoinColumn(name = "company_cd")
    private CompanyInfo companyInfo;

    @Builder
    public MovieCompanies(MovieInfo movieInfo, CompanyInfo companyInfo) {
        this.movieInfo = movieInfo;
        this.companyInfo = companyInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieCompanies movieCompanies)) return false;
        return movieInfo != null && companyInfo != null && movieInfo.equals(movieCompanies.movieInfo) && companyInfo.equals(movieCompanies.companyInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieInfo, companyInfo);
    }
}
