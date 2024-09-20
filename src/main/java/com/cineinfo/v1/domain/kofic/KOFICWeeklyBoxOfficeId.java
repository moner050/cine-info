package com.cineinfo.v1.domain.kofic;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class KOFICWeeklyBoxOfficeId implements Serializable {

    @Comment("영화 이름")
    @Column(name = "movie_nm", length = 200)
    private String movieNm;

    @Comment("영화 개봉일자")
    @Column(name = "open_dt")
    private LocalDate openDt;

    @Comment("한국/외국 영화 분류")
    @Column(name = "rep_nation_cd", length = 1, nullable = false)
    private String repNationCd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KOFICWeeklyBoxOfficeId koficWeeklyBoxOfficeId)) return false;
        return movieNm != null && openDt != null && repNationCd != null && movieNm.equals(koficWeeklyBoxOfficeId.movieNm) && openDt.equals(koficWeeklyBoxOfficeId.openDt) && repNationCd.equals(koficWeeklyBoxOfficeId.repNationCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieNm, openDt);
    }
}
