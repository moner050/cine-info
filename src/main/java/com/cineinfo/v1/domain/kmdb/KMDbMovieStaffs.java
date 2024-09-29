package com.cineinfo.v1.domain.kmdb;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("KMDb 영화 스태프 정보")
@Entity(name = "kmdb_movie_staffs")
public class KMDbMovieStaffs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("스태프 고유 아이디")
    @Column(name = "staff_id")
    private Long staffId;

    @Comment("영화 아이디")
    @ManyToOne(optional = false)
    @JoinColumn(name = "movieId")
    private KMDbMovieInfo kmdbMovieInfo;

    @Comment("스태프 이름")
    @Column(name = "staff_nm", length = 200)
    private String staffNm;

    @Comment("스태프 영어 이름")
    @Column(name = "staff_en_nm", length = 400)
    private String staffEnNm;

    @Comment("스태프 크레딧 명칭")
    @Column(name = "staff_role_group", length = 100)
    private String staffRoleGroup;

    @Comment("스태프 배역")
    @Column(name = "staff_role", length = 100)
    private String staffRole;

    @Comment("스태프 기타")
    @Column(name = "staff_etc", length = 2000)
    private String staffEtc;

    @Builder
    public KMDbMovieStaffs(Long staffId, KMDbMovieInfo kmdbMovieInfo, String staffNm, String staffEnNm, String staffRoleGroup, String staffRole, String staffEtc) {
        this.staffId = staffId;
        this.kmdbMovieInfo = kmdbMovieInfo;
        this.staffNm = staffNm;
        this.staffEnNm = staffEnNm;
        this.staffRoleGroup = staffRoleGroup;
        this.staffRole = staffRole;
        this.staffEtc = staffEtc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KMDbMovieStaffs kmdbMovieStaffs)) return false;
        return staffId != null && staffId.equals(kmdbMovieStaffs.staffId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffId);
    }
}
