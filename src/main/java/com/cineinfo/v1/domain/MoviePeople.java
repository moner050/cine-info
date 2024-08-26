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
@Comment("영화인 정보")
@Entity(name = "movie_people")
public class MoviePeople {

    @Id
    @Column(name = "people_cd", length = 8)
    private String peopleCd;

    @Comment("영화인 이름")
    @Column(name = "people_nm", nullable = false, length = 100)
    public String peopleNm;

    @Comment("영화인 영어 이름")
    @Column(name = "people_nm_en", length = 100)
    public String peopleNmEn;

    @Comment("성별")
    @Column(name = "sex", nullable = false, length = 4)
    public String sex;

    @Comment("영화인 분류명")
    @Column(name = "rep_role_nm", length = 50)
    public String repRoleNm;

    @Comment("관련 URL")
    @Column(name = "hompages", length = 500)
    public String hompages;

}
