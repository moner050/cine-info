package com.cineinfo.v1.dto.kofic.response.dailyboxoffice;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DailyBoxOfficeResultRes {

    private String boxofficeType;
    private String showRange;
    private List<DailyBoxOfficeListRes> dailyBoxOfficeList;
}
