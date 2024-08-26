package com.cineinfo.v1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Comment("공통코드")
@Entity(name = "com_code")
public class ComCode {

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
    @Column(name = "eng_nm", nullable = false, length = 200)
    private String engNm;

}
