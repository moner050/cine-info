package com.cineinfo.v1.dto.kofic.response.movie_list;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DirectorsRes {

    private String peopleNm;

    @Override
    public String toString() {
        return peopleNm + ", ";
    }
}
