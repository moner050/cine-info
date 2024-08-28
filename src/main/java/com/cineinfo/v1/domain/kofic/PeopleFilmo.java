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
@Entity(name = "people_filmo")
public class PeopleFilmo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_cd")
    private MovieInfo movieInfo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "people_cd")
    private MoviePeople moviePeople;

    @Comment("영화명")
    @Column(name = "movie_nm", nullable = false, length = 200)
    private String movieNm;

    @Comment("참여분야")
    @Column(name = "movie_part_nm", length = 50)
    private String moviePartNm;

    @Builder
    public PeopleFilmo(MovieInfo movieInfo, MoviePeople moviePeople, String movieNm, String moviePartNm) {
        this.movieInfo = movieInfo;
        this.moviePeople = moviePeople;
        this.movieNm = movieNm;
        this.moviePartNm = moviePartNm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeopleFilmo peopleFilmo)) return false;
        return id != null && id.equals(peopleFilmo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
