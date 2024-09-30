package com.cineinfo.v1.dto.admin.kmdb.response.movie_list;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommCodeRes {

    @JsonProperty("CodeNm")
    private String CodeNm;
    @JsonProperty("CodeNo")
    private String CodeNo;
}
