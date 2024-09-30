package com.cineinfo.v1.dto.admin.kmdb.response.movie_list;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DirectorRes {
    private String directorNm;
    private String directorEnNm;
    private String directorId;
}
