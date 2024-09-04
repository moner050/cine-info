package com.cineinfo.v1.dto.kmdb.response.movie_list;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResultRes {

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
    private String repRatDate;
    private String repRlsDate;
    private RatingsRes ratings;
    private String keyword;
    private String posters;
    private String stlls;
    private StaffsRes staffs;
    private VodsRes vods;
    private String openThtr;
    private String screenArea;
    private String screenCnt;
    private String salesAcc;
    private String audiAcc;
    private String statSouce;
    private String statDate;
    private String themeSong;
    private String soundtrack;
    private String fLocation;
    private String awards1;
    private String awards2;
    private String regDate;
    private String modDate;
}
