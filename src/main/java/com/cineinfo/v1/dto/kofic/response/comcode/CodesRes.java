package com.cineinfo.v1.dto.kofic.response.comcode;

import com.cineinfo.v1.domain.kofic.KOFICComCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodesRes {

    private String fullCd;
    private String korNm;
    private String engNm;

    public static KOFICComCode toEntity(CodesRes codesRes, String summaryCd) {
        return KOFICComCode.builder()
                .fullCd(codesRes.getFullCd())
                .summaryCd(summaryCd)
                .korNm(codesRes.getKorNm())
                .engNm(codesRes.getEngNm())
                .build();
    }
}
