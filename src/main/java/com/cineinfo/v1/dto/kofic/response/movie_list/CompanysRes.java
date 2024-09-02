package com.cineinfo.v1.dto.kofic.response.movie_list;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanysRes {

    private String companyCd;
    private String companyNm;

    @Override
    public String toString() {
        return companyNm;
    }
}
