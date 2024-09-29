package com.cineinfo.v1.dto.admin.kofic.response;

import com.cineinfo.v1.dto.admin.kofic.response.dailyboxoffice.DailyBoxOfficeResultRes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SearchKOFICDailyBoxOfficeRes {

    private DailyBoxOfficeResultRes boxOfficeResult;
    private FaultInfoRes faultInfo;
}
