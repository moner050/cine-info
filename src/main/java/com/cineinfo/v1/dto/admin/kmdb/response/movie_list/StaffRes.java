package com.cineinfo.v1.dto.admin.kmdb.response.movie_list;

import com.cineinfo.v1.domain.admin.kmdb.KMDbMovieInfo;
import com.cineinfo.v1.domain.admin.kmdb.KMDbMovieStaffs;
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

    public KMDbMovieStaffs toEntity(KMDbMovieInfo kmdbMovieInfo) {
        return KMDbMovieStaffs.builder()
                .kmdbMovieInfo(kmdbMovieInfo)
                .staffNm(staffNm)
                .staffEnNm(staffEnNm)
                .staffRoleGroup(staffRoleGroup)
                .staffRole(staffRole)
                .staffEtc(staffEtc)
                .build();
    }
}
