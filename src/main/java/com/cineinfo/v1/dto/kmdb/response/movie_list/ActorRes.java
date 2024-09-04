package com.cineinfo.v1.dto.kmdb.response.movie_list;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActorRes {
    private String actorNm;
    private String actorEnNm;
    private String actorId;
}
