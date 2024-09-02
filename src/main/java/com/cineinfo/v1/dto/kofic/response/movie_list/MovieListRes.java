package com.cineinfo.v1.dto.kofic.response.movie_list;

import com.cineinfo.v1.domain.kofic.MovieInfo;
import lombok.*;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MovieListRes {

    private String movieCd;
    private String movieNm;
    private String movieNmEn;
    private String prdtYear;
    private String openDt;
    private String typeNm;
    private String prdtStatNm;
    private String nationAlt;
    private String genreAlt;
    private String repNationNm;
    private String repGenreNm;
    private List<DirectorsRes> directors;
    private List<CompanysRes> companys;

    public static MovieInfo toEntity(MovieListRes movieListRes) {
        return MovieInfo.builder()
                .movieCd(movieListRes.getMovieCd())
                .movieNm(movieListRes.getMovieNm())
                .movieNmEn(movieListRes.getMovieNmEn())
                .prdtYear(movieListRes.getPrdtYear())
                .openDt(movieListRes.getOpenDt())
                .typeNm(movieListRes.getTypeNm())
                .prdtStatNm(movieListRes.getPrdtStatNm())
                .nationAlt(movieListRes.getNationAlt())
                .genreAlt(movieListRes.getGenreAlt())
                .repNationNm(movieListRes.getRepNationNm())
                .repGenreNm(movieListRes.getRepGenreNm())
                .directorPeopleNms(listToString(movieListRes.getDirectors()))
                .companiesNms(listToString(movieListRes.getCompanys()))
                .build();
    }

    public static <T> String listToString(List<T> objects) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objects.size(); i++) {
            sb.append(objects.get(i).toString());
            if(i != objects.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
