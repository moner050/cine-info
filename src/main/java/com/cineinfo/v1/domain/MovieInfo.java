package com.cineinfo.v1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Entity(name = "movie_info")
@Getter
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
    @Column(name = "director_people_nms", length = 200)
    private String directorPeopleNms;

}