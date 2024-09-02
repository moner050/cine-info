package com.cineinfo.v1.domain.kofic;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("영화 정보")
@Entity(name = "movie_info")
public class MovieInfo {

    @Id
    @Comment("영화코드")
    @Column(name = "movie_cd", nullable = false, length = 8)
    private String movieCd;

    @Comment("영화명")
    @Column(name = "movie_nm", nullable = false, length = 200)
    private String movieNm;

    @Comment("영화명(영문)")
    @Column(name = "movie_nm_en", length = 200)
    private String movieNmEn;

    @Comment("제작년도")
    @Column(name = "prdt_year", nullable = false, length = 4)
    private String prdtYear;

    @Comment("개봉일")
    @Column(name = "open_dt", length = 8)
    private String openDt;

    @Comment("영화유형")
    @Column(name = "type_nm", length = 10)
    private String typeNm;

    @Comment("제작상태")
    @Column(name = "prdt_stat_nm", length = 6)
    private String prdtStatNm;

    @Comment("제작국가(전체)")
    @Column(name = "nation_alt", length = 30)
    private String nationAlt;

    @Comment("영화장르(전체)")
    @Column(name = "genre_alt", length = 30)
    private String genreAlt;

    @Comment("대표 제작국가명")
    @Column(name = "rep_nation_nm", length = 20)
    private String repNationNm;

    @Comment("대표 장르명")
    @Column(name = "rep_genre_nm", length = 20)
    private String repGenreNm;

    @Comment("영화감독명들")
    @Column(name = "director_people_nms", length = 1000)
    private String directorPeopleNms;

    @Comment("제작사들")
    @Column(name = "company_nms", length = 2000)
    private String companiesNms;

    @Builder
    public MovieInfo(String movieCd, String movieNm, String movieNmEn, String prdtYear, String openDt, String typeNm, String prdtStatNm, String nationAlt, String genreAlt, String repNationNm, String repGenreNm, String directorPeopleNms, String companiesNms) {
        this.movieCd = movieCd;
        this.movieNm = movieNm;
        this.movieNmEn = movieNmEn;
        this.prdtYear = prdtYear;
        this.openDt = openDt;
        this.typeNm = typeNm;
        this.prdtStatNm = prdtStatNm;
        this.nationAlt = nationAlt;
        this.genreAlt = genreAlt;
        this.repNationNm = repNationNm;
        this.repGenreNm = repGenreNm;
        this.directorPeopleNms = directorPeopleNms;
        this.companiesNms = companiesNms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieInfo movieInfo)) return false;
        return movieCd != null && movieCd.equals(movieInfo.movieCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieCd);
    }
}
