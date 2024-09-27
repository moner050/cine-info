package com.cineinfo.v1.dto.kofic.response;

import com.cineinfo.v1.dto.kofic.response.weeklyboxoffice.WeeklyBoxOfficeResultRes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SearchKOFICWeeklyBoxOfficeRes {

    private WeeklyBoxOfficeResultRes boxOfficeResult;
    private FaultInfoRes faultInfo;
}
