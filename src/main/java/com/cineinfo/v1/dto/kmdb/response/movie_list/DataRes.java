package com.cineinfo.v1.dto.kmdb.response.movie_list;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DataRes {

    @JsonProperty("CollName")
    private String collName;
    @JsonProperty("TotalCount")
    private Long totalCount;
    @JsonProperty("Count")
    private Long count;
    @JsonProperty("Result")
    private List<ResultRes> result;
}
