package com.cineinfo.v1.domain.kofic;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("영화사 필모그래피")
@Entity(name = "kofic_company_filmo")
public class KOFICCompanyFilmo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Comment("영화사 코드")
    @ManyToOne(optional = false)
    @JoinColumn(name = "company_cd")
    private KOFICCompanyInfo companyInfo;

    @Comment("영화코드")
    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_cd")
    private KOFICMovieInfo movieInfo;

    @Comment("영화명")
    @Column(name = "movie_nm", nullable = false, length = 200)
    private String movieNm;

    @Comment("영화사 참여 분류명")
    @Column(name = "company_part_nm", length = 100)
    private String companyPartNm;

    @Builder
    public KOFICCompanyFilmo(KOFICCompanyInfo KOFICCompanyInfo, KOFICMovieInfo KOFICMovieInfo, String movieNm, String companyPartNm) {
        this.companyInfo = KOFICCompanyInfo;
        this.movieInfo = KOFICMovieInfo;
        this.movieNm = movieNm;
        this.companyPartNm = companyPartNm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KOFICCompanyFilmo KOFICCompanyFilmo)) return false;
        return id != null && id.equals(KOFICCompanyFilmo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
