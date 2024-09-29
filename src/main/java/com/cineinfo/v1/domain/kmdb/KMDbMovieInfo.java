package com.cineinfo.v1.domain.kmdb;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("KMDb 영화 정보")
@Entity(name = "kmdb_movie_info")
public class KMDbMovieInfo {

    @Id
    @Comment("영화 아이디")
    @Column(name = "movie_id", length = 6)
    private String movieId;

    @Comment("영화명")
    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Comment("영문제명")
    @Column(name = "title_eng", length = 200)
    private String titleEng;

    @Comment("원제명")
    @Column(name = "title_org", length = 200)
    private String titleOrg;

    @Comment("제작년도")
    @Column(name = "prod_year", nullable = false, length = 4)
    private String prodYear;

    @Comment("제작국가")
    @Column(name = "nation", length = 40)
    private String nation;

    @Comment("제작사")
    @Column(name = "company", length = 200)
    private String company;

    @Comment("대표상영시간")
    @Column(name = "runtime", length = 5)
    private String runtime;

    @Comment("장르")
    @Column(name = "genre", length = 200)
    private String genre;

    @Comment("유형구분")
    @Column(name = "type", length = 20)
    private String type;

    @Comment("용도구분")
    @Column(name = "purpose", length = 20)
    private String purpose;

    @Comment("영상 내 에피소드")
    @Column(name = "episodes", length = 5000)
    private String episodes;

    @Comment("심의여부")
    @Column(name = "rated_yn", length = 2)
    private String ratedYn;

    @Comment("대표심의일")
    @Column(name = "rep_rate_date")
    private LocalDate repRateDate;

    @Comment("대표개봉일")
    @Column(name = "rep_rls_date")
    private LocalDate repRlsDate;

    @Comment("키워드")
    @Column(name = "keywords", length = 200)
    private String keywords;

    @Comment("누적매출액")
    @Column(name = "sales_acc", length = 20)
    private String salesAcc;

    @Comment("누적관람인원")
    @Column(name = "audi_acc", length = 20)
    private String audiAcc;

    @Comment("출처")
    @Column(name = "stat_source", length = 50)
    private String statSource;

    @Comment("기준일")
    @Column(name = "stat_date", length = 10)
    private String statDate;

    @Comment("주제곡")
    @Column(name = "theme_song", length = 200)
    private String themeSong;

    @Comment("촬영장소")
    @Column(name = "f_location", length = 3000)
    private String fLocation;

    @Comment("영화제수상내역")
    @Column(name = "awards1", length = 3000)
    private String awards1;

    @Comment("수상내역 ")
    @Column(name = "awards2", length = 3000)
    private String awards2;

    @Comment("등록일")
    @Column(name = "reg_date")
    private LocalDate regDate;

    @Comment("최종수정일")
    @Column(name = "mod_date")
    private LocalDate modDate;

    @Builder
    public KMDbMovieInfo(String movieId, String title, String titleEng, String titleOrg, String prodYear, String nation, String company, String runtime, String genre, String type, String purpose, String episodes, String ratedYn, LocalDate repRateDate, LocalDate repRlsDate, String keywords, String salesAcc, String audiAcc, String statSource, String statDate, String themeSong, String fLocation, String awards1, String awards2, LocalDate regDate, LocalDate modDate) {
        this.movieId = movieId;
        this.title = title;
        this.titleEng = titleEng;
        this.titleOrg = titleOrg;
        this.prodYear = prodYear;
        this.nation = nation;
        this.company = company;
        this.runtime = runtime;
        this.genre = genre;
        this.type = type;
        this.purpose = purpose;
        this.episodes = episodes;
        this.ratedYn = ratedYn;
        this.repRateDate = repRateDate;
        this.repRlsDate = repRlsDate;
        this.keywords = keywords;
        this.salesAcc = salesAcc;
        this.audiAcc = audiAcc;
        this.statSource = statSource;
        this.statDate = statDate;
        this.themeSong = themeSong;
        this.fLocation = fLocation;
        this.awards1 = awards1;
        this.awards2 = awards2;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KMDbMovieInfo kmdbMovieInfo)) return false;
        return movieId != null && movieId.equals(kmdbMovieInfo.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId);
    }
}
