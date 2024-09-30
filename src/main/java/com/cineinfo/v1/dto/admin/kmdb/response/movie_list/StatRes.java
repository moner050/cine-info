package com.cineinfo.v1.dto.admin.kmdb.response.movie_list;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StatRes {

    private String screenArea;
    private String screenCnt;
    private String salesAcc;
    private String audiAcc;
    private String statSouce;
    private String statDate;
}
