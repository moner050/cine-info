package com.cineinfo.v1.dto.kofic.response.weeklyboxoffice;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WeeklyBoxOfficeResultRes {

    private String boxofficeType;
    private String showRange;
    private String yearWeekTime;
    private List<WeeklyBoxOfficeListRes> weeklyBoxOfficeList;
}
