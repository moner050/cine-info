package com.cineinfo.v1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Entity(name = "company_info")
@Getter
public class CompanyInfo {

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

}
