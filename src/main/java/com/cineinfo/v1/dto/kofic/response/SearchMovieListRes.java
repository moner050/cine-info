package com.cineinfo.v1.dto.kofic.response;

import com.cineinfo.v1.dto.kofic.response.movie_list.MovieListResultRes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SearchMovieListRes {

    private MovieListResultRes movieListResult;
}
