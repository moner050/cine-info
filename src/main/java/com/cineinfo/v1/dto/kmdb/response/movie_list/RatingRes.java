package com.cineinfo.v1.dto.kmdb.response.movie_list;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RatingRes {
    private String ratingMain;
    private String ratingDate;
    private String ratingNo;
    private String ratingGrade;
    private String releaseDate;
    private String runtime;
}
