package com.cineinfo.v1.dto.kofic.response.movie_list;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MovieListResultRes {

    private Long totCnt;
    private String source;
    private List<MovieListRes> movieList;

}
