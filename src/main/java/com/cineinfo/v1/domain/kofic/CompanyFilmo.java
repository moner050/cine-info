package com.cineinfo.v1.domain.kofic;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("영화사 필모그래피")
@Entity(name = "company_filmo")
public class CompanyFilmo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Comment("영화사 코드")
    @ManyToOne(optional = false)
    @JoinColumn(name = "company_cd")
    private CompanyInfo companyInfo;

    @Comment("영화코드")
    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_cd")
    private MovieInfo movieInfo;

    @Comment("영화명")
    @Column(name = "movie_nm", nullable = false, length = 200)
    private String movieNm;

    @Comment("영화사 참여 분류명")
    @Column(name = "company_part_nm", length = 100)
    private String companyPartNm;

    @Builder
    public CompanyFilmo(CompanyInfo companyInfo, MovieInfo movieInfo, String movieNm, String companyPartNm) {
        this.companyInfo = companyInfo;
        this.movieInfo = movieInfo;
        this.movieNm = movieNm;
        this.companyPartNm = companyPartNm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyFilmo companyFilmo)) return false;
        return id != null && id.equals(companyFilmo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
