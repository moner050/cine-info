package com.cineinfo.v1.domain.kofic;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("영화인 필모그래피")
@Entity(name = "kofic_people_filmo")
public class KOFICPeopleFilmo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_cd")
    private KOFICMovieInfo movieInfo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "people_cd")
    private KOFICMoviePeople moviePeople;

    @Comment("영화명")
    @Column(name = "movie_nm", nullable = false, length = 200)
    private String movieNm;

    @Comment("참여분야")
    @Column(name = "movie_part_nm", length = 50)
    private String moviePartNm;

    @Builder
    public KOFICPeopleFilmo(KOFICMovieInfo KOFICMovieInfo, KOFICMoviePeople KOFICMoviePeople, String movieNm, String moviePartNm) {
        this.movieInfo = KOFICMovieInfo;
        this.moviePeople = KOFICMoviePeople;
        this.movieNm = movieNm;
        this.moviePartNm = moviePartNm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KOFICPeopleFilmo KOFICPeopleFilmo)) return false;
        return id != null && id.equals(KOFICPeopleFilmo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
