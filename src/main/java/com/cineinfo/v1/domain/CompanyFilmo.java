package com.cineinfo.v1.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Entity(name = "company_filmo")
@Getter
public class CompanyFilmo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_cd")
    private CompanyInfo companyInfo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_cd")
    private MovieInfo movieInfo;

    @Comment("영화명")
    @Column(name = "movie_nm", nullable = false, length = 200)
    private String movieNm;

    @Comment("영화사 참여 분류명")
    @Column(name = "company_part_nm", length = 100)
    private String companyPartNm;
}
