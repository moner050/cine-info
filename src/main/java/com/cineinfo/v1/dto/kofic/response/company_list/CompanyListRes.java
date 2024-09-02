package com.cineinfo.v1.dto.kofic.response.company_list;

import com.cineinfo.v1.domain.kofic.CompanyInfo;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompanyListRes {

    private String companyCd;
    private String companyNm;
    private String companyNmEn;
    private String companyPartNames;
    private String ceoNm;
//    private String filmoNames;

    public static CompanyInfo toEntity(CompanyListRes companyListRes) {
        return CompanyInfo.builder()
                .companyCd(companyListRes.getCompanyCd())
                .companyNm(companyListRes.getCompanyNm())
                .companyNmEn(companyListRes.getCompanyNmEn())
                .companyPartNames(companyListRes.getCompanyPartNames())
                .ceoNm(companyListRes.getCeoNm())
                .build();
    }
}
