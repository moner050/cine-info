package com.cineinfo.v1.dto.admin.kmdb.response.movie_list;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeRes {

    @JsonProperty("CodeNm")
    private String codeNm;
    @JsonProperty("CodeNo")
    private String codeNo;
}
