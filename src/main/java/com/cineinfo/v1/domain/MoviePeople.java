package com.cineinfo.v1.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("영화인 정보")
@Entity(name = "movie_people")
public class MoviePeople {

    @Id
    @Column(name = "people_cd", length = 8)
    private String peopleCd;

    @Comment("영화인 이름")
    @Column(name = "people_nm", nullable = false, length = 100)
    private String peopleNm;

    @Comment("영화인 영어 이름")
    @Column(name = "people_nm_en", length = 100)
    private String peopleNmEn;

    @Comment("성별")
    @Column(name = "sex", nullable = false, length = 4)
    private String sex;

    @Comment("영화인 분류명")
    @Column(name = "rep_role_nm", length = 50)
    private String repRoleNm;

    @Comment("관련 URL")
    @Column(name = "hompages", length = 500)
    private String hompages;

    @Builder
    public MoviePeople(String peopleCd, String peopleNm, String peopleNmEn, String sex, String repRoleNm, String hompages) {
        this.peopleCd = peopleCd;
        this.peopleNm = peopleNm;
        this.peopleNmEn = peopleNmEn;
        this.sex = sex;
        this.repRoleNm = repRoleNm;
        this.hompages = hompages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoviePeople moviePeople)) return false;
        return peopleCd != null && peopleCd.equals(moviePeople.peopleCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peopleCd);
    }
}
