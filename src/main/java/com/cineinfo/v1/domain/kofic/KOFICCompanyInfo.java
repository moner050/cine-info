package com.cineinfo.v1.domain.kofic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("영화사 정보")
@Entity(name = "kofic_company_info")
public class KOFICCompanyInfo {

    @Id
    @Comment("영화사 코드")
    @Column(name = "company_cd", nullable = false, length = 8)
    private String companyCd;

    @Comment("영화사명")
    @Column(name = "company_nm", nullable = false, length = 200)
    private String companyNm;

    @Comment("영화사명(영문)")
    @Column(name = "company_nm_en", length = 200)
    private String companyNmEn;

    @Comment("대표자명")
    @Column(name = "ceo_nm", nullable = false, length = 100)
    private String ceoNm;

    @Comment("영화사 분류")
    @Column(name = "company_part_names", length = 3000)
    private String companyPartNames;

    @Builder
    public KOFICCompanyInfo(String companyCd, String companyNm, String companyNmEn, String ceoNm, String companyPartNames) {
        this.companyCd = companyCd;
        this.companyNm = companyNm;
        this.companyNmEn = companyNmEn;
        this.ceoNm = ceoNm;
        this.companyPartNames = companyPartNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KOFICCompanyInfo KOFICCompanyInfo)) return false;
        return companyCd != null && companyCd.equals(KOFICCompanyInfo.companyCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyCd);
    }
}
