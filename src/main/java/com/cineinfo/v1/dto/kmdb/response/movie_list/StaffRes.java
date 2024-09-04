package com.cineinfo.v1.dto.kmdb.response.movie_list;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StaffRes {
    private String staffNm;
    private String staffEnNm;
    private String staffRoleGroup;
    private String staffRole;
    private String staffEtc;
    private String staffId;
}
