package com.cineinfo.v1.domain.kofic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("공통코드")
@Entity(name = "kofic_com_code")
public class KOFICComCode {

    @Id
    @Comment("상위코드")
    @Column(name = "full_cd", length = 20)
    private String fullCd;

    @Comment("간략화 상위코드")
    @Column(name = "summary_cd", nullable = false, length = 10)
    private String summaryCd;

    @Comment("한국명")
    @Column(name = "kor_nm", nullable = false, length = 200)
    private String korNm;

    @Comment("영어명")
    @Column(name = "eng_nm", length = 200)
    private String engNm;

    @Builder
    public KOFICComCode(String fullCd, String summaryCd, String korNm, String engNm) {
        this.fullCd = fullCd;
        this.summaryCd = summaryCd;
        this.korNm = korNm;
        this.engNm = engNm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KOFICComCode KOFICComCode)) return false;
        return fullCd != null && fullCd.equals(KOFICComCode.fullCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullCd);
    }
}
