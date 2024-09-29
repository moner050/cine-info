package com.cineinfo.v1.dto.admin.kmdb.response.movie_list;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommCodesRes {

    @JsonProperty("CommCode")
    private List<CommCodeRes> commCode;
}
