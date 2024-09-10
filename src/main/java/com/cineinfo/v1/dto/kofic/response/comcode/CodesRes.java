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

    public KOFICComCode toEntity(String summaryCd) {
        return KOFICComCode.builder()
                .fullCd(fullCd)
                .summaryCd(summaryCd)
                .korNm(korNm)
                .engNm(engNm)
                .build();
    }
}
