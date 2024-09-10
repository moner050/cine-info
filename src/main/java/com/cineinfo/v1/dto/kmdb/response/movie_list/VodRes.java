package com.cineinfo.v1.dto.kmdb.response.movie_list;

import com.cineinfo.v1.domain.kmdb.KMDbMovieInfo;
import com.cineinfo.v1.domain.kmdb.KMDbMovieVods;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VodRes {
    private String vodClass;
    private String vodUrl;

    public KMDbMovieVods toEntity(KMDbMovieInfo kmdbMovieInfo) {
        return KMDbMovieVods.builder()
                .kmdbMovieInfo(kmdbMovieInfo)
                .vodName(vodClass)
                .vodUrl(vodUrl)
                .build();
    }
}
