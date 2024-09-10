package com.cineinfo.v1.dto.kmdb.response.movie_list;

import com.cineinfo.v1.domain.kmdb.KMDbMovieInfo;
import com.cineinfo.v1.domain.kmdb.KMDbMoviePlots;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlotRes {
    private String plotLang;
    private String plotText;

    public KMDbMoviePlots toEntity(KMDbMovieInfo kmdbMovieInfo) {
        return KMDbMoviePlots.builder()
                .kmdbMovieInfo(kmdbMovieInfo)
                .plotLang(plotLang)
                .plotText(plotText)
                .build();
    }
}
