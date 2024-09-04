package com.cineinfo.v1.dto.kmdb.response.movie_list;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DataRes {

    private Long totalCount;
    private Long count;
    private List<ResultRes> result;
}
