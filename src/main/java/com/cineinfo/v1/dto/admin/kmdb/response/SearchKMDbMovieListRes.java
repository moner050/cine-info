package com.cineinfo.v1.dto.admin.kmdb.response;

import com.cineinfo.v1.dto.admin.kmdb.response.movie_list.DataRes;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchKMDbMovieListRes {

    @JsonProperty("Query")
    private String query;
    @JsonProperty("KMAQuery")
    private String kmaQuery;
    @JsonProperty("TotalCount")
    private Integer totalCount;
    @JsonProperty("Data")
    private List<DataRes> data;
    @JsonProperty("Error")
    private String error;

}
