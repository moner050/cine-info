package com.cineinfo.v1.dto.kmdb.response.movie_list;

import com.cineinfo.v1.domain.kmdb.KMDbMovieInfo;
import com.cineinfo.v1.domain.kmdb.KMDbMoviePosters;
import com.cineinfo.v1.domain.kmdb.KMDbMovieStills;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResultRes {

    @JsonProperty("DOCID")
    private String DOCID;
    private String movieId;
    private String movieSeq;
    private String title;
    private String titleEng;
    private String titleOrg;
    private String titleEtc;
    private String prodYear;
    private DirectorsRes directors;
    private ActorsRes actors;
    private String nation;
    private String company;
    private PlotsRes plots;
    private String runtime;
    private String rating;
    private String genre;
    private String kmdbUrl;
    private String type;
    private String use;
    private String episodes;
    private String ratedYn;
    private LocalDate repRatDate;
    private LocalDate repRlsDate;
    private RatingsRes ratings;
    private String keywords;
    private String posters;
    private String stlls;
    private StaffsRes staffs;
    private VodsRes vods;
    private String openThtr;
    private List<StatRes> stat;
    private String screenArea;
    private String screenCnt;
    private String salesAcc;
    private String audiAcc;
    private String statSouce;
    private String statDate;
    private String themeSong;
    private String soundtrack;
    @JsonProperty("fLocation")
    private String fLocation;
    @JsonProperty("Awards1")
    private String awards1;
    @JsonProperty("Awards2")
    private String awards2;
    private LocalDate regDate;
    private LocalDate modDate;
    @JsonProperty("Codes")
    private CodesRes codes;
    @JsonProperty("CommCodes")
    private CommCodesRes commCodes;
    @JsonProperty("ALIAS")
    private String alias;

    public KMDbMovieInfo toEntity() {
        return KMDbMovieInfo.builder()
                .movieId(DOCID)
                .title(title)
                .titleEng(titleEng)
                .titleOrg(titleOrg)
                .prodYear(prodYear)
                .nation(nation)
                .company(company)
                .runtime(runtime)
                .genre(genre)
                .type(type)
                .purpose(use)
                .episodes(episodes)
                .ratedYn(ratedYn)
                .repRateDate(repRatDate)
                .repRlsDate(repRlsDate)
                .keywords(keywords)
                .salesAcc(salesAcc)
                .audiAcc(audiAcc)
                .statSource(statSouce)
                .statDate(statDate)
                .themeSong(themeSong)
                .soundTrack(soundtrack)
                .fLocation(fLocation)
                .awards1(awards1)
                .awards2(awards2)
                .regDate(regDate)
                .modDate(modDate)
                .build();
    }

    public KMDbMoviePosters toPosterEntity(KMDbMovieInfo kmdbMovieInfo, String url) {
        return KMDbMoviePosters.builder()
                .kmdbMovieInfo(kmdbMovieInfo)
                .url(url)
                .build();
    }

    public KMDbMovieStills toStillsEntity(KMDbMovieInfo kmdbMovieInfo, String url) {
        return KMDbMovieStills.builder()
                .kmdbMovieInfo(kmdbMovieInfo)
                .url(url)
                .build();
    }
}
