package com.cineinfo.v1.dto.admin;

public record DailyBoxOfficeRequest(String startDate, String endDate, String repNationCd) {

    public static DailyBoxOfficeRequest of(String startDate, String endDate, String repNationCd) {
        return new DailyBoxOfficeRequest(startDate, endDate, repNationCd);
    }

}
