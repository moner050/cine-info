package com.cineinfo.v1.dto.kofic.response;

import com.cineinfo.v1.dto.kofic.response.company_list.CompanyListResultRes;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SearchKOFICCompanyListRes {

    private CompanyListResultRes companyListResult;
}
