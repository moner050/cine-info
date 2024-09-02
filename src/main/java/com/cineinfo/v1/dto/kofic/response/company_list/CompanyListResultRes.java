package com.cineinfo.v1.dto.kofic.response.company_list;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanyListResultRes {

    private Integer totCnt;
    private List<CompanyListRes> companyList;
}
