package com.cineinfo.v1.dto.kmdb.response;

import com.cineinfo.v1.dto.kmdb.response.movie_list.DataRes;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchKMDbMovieListRes {

    private List<DataRes> data;
}
