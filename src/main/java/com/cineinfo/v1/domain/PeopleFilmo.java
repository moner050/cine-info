package com.cineinfo.v1.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
}
