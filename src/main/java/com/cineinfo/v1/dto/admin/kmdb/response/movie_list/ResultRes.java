package com.cineinfo.v1.dto.admin.kmdb.response.movie_list;

import com.cineinfo.v1.domain.admin.kmdb.KMDbMovieInfo;
import com.cineinfo.v1.domain.admin.kmdb.KMDbMoviePosters;
import com.cineinfo.v1.domain.admin.kmdb.KMDbMovieStills;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private String repRatDate;
    private String repRlsDate;
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
    private String regDate;
    private String modDate;
    @JsonProperty("Codes")
    private CodesRes codes;
    @JsonProperty("CommCodes")
    private CommCodesRes commCodes;
    @JsonProperty("ALIAS")
    private String alias;

    // 날짜일이 00일인 데이터가 존재해서 00 -> 01 로 바꾸는 메서드 추가
    // 날짜달이 00월인 데이터가 존재해서 00 -> 01 로 바꾸는 메서드 추가
    // 날짜가 8글자가 아닌 7글자인 데이터가 존재해서 해당 데이터가 있으면 분기처리
    public String replaceDate(String date) {
        StringBuilder newDate = new StringBuilder();
        StringBuilder newMonth = new StringBuilder();
        StringBuilder newDay = new StringBuilder();

        String year = date.substring(0, 4);
        date = date.trim();

        if(date.length() == 7) {
            // 만약 달 형식이 이상하면 분기처리
            // 2024131 -> 20240131
            if(Integer.parseInt(date.substring(4, 6)) > 12) {
                newMonth.append("0").append(date.charAt(4));
                newDay.append(date, 5, 7);
            }
            // 2024128 -> 20241208
            else {
                newMonth.append(date, 4, 6);
                newDay.append("0").append(date.charAt(6));
            }
        }
        else {
            newMonth.append(date, 4, 6);
            newDay.append(date, 6, 8);
        }

        if(newMonth.toString().equals("00")) {
            newMonth.delete(0, newMonth.length());
            newMonth.append("01");
        }
        if(newDay.toString().equals("00")) {
            newDay.delete(0, newDay.length());
            newDay.append("01");
        }

        return newDate.append(year).append(newMonth).append(newDay).toString();
    }

    public KMDbMovieInfo toEntity() {
        salesAcc = salesAcc.isEmpty() ? null : salesAcc.split("\\|")[0];
        audiAcc = audiAcc.isEmpty() ? null : audiAcc.split("\\|")[0];
        statSouce = statSouce.isEmpty() ? "" : statSouce.split("\\|")[0];
        statDate = statDate.isEmpty() ? "" : statDate.split("\\|")[0];

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
                .repRateDate(repRatDate.isEmpty() ? null : LocalDate.parse(replaceDate(repRatDate), DateTimeFormatter.ofPattern("yyyyMMdd")))
                .repRlsDate(repRlsDate.isEmpty() ? null : LocalDate.parse(replaceDate(repRlsDate), DateTimeFormatter.ofPattern("yyyyMMdd")))
                .keywords(keywords)
                .salesAcc(salesAcc != null ? Long.parseLong(salesAcc) : null)
                .audiAcc(audiAcc != null ? Long.parseLong(audiAcc) : null)
                .statSource(statSouce)
                .statDate(statDate.isEmpty() ? null : LocalDate.parse(replaceDate(statDate), DateTimeFormatter.ofPattern("yyyyMMdd")))
                .themeSong(themeSong)
                .fLocation(fLocation)
                .awards1(awards1)
                .awards2(awards2)
                .regDate(regDate.isEmpty() ? null : LocalDate.parse(replaceDate(regDate), DateTimeFormatter.ofPattern("yyyyMMdd")))
                .modDate(modDate.isEmpty() ? null : LocalDate.parse(replaceDate(modDate), DateTimeFormatter.ofPattern("yyyyMMdd")))
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
