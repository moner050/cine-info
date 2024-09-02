package com.cineinfo.v1.domain.kofic.constant;

import lombok.Getter;

public enum KOFICSummaryCd {

    WIDE_AREA_CD("지역코드", "0105000000"),
    MOVIE_TYPE_CD("영화유형코드", "2201"),
    REP_NATION_CD("국적코드", "2204"),
    COMPANY_PART_CD("영화사분류코드", "2601");

    @Getter private final String description;
    @Getter private final String summaryCd;

    KOFICSummaryCd(String description, String summaryCd) {
        this.description = description;
        this.summaryCd = summaryCd;
    }
}
