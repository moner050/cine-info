package com.cineinfo.v1.dto.kmdb.response.movie_list;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VodsRes {
    private List<VodRes> vod;
}
