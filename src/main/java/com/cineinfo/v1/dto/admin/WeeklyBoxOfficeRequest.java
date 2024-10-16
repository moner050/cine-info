package com.cineinfo.v1.dto.admin;

public record WeeklyBoxOfficeRequest(String startDate, String endDate, String repNationCd, String weekGb) {

    public static WeeklyBoxOfficeRequest of(String startDate, String endDate, String repNationCd, String weekGb) {
        return new WeeklyBoxOfficeRequest(startDate, endDate, repNationCd, weekGb);
    }


}
