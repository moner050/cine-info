package com.cineinfo.v1.dto.kofic.response;

import com.cineinfo.v1.dto.kofic.response.dailyboxoffice.DailyBoxOfficeResultRes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SearchKOFICDailyBoxOfficeRes {

    private DailyBoxOfficeResultRes boxOfficeResult;
}
