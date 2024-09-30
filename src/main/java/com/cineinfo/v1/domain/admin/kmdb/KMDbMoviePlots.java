package com.cineinfo.v1.domain.admin.kmdb;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("KMDb 영화 줄거리 정보")
@Entity(name = "kmdb_movie_plots")
public class KMDbMoviePlots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plot_id")
    private Long plotId;

    @Comment("영화 아이디")
    @ManyToOne(optional = false)
    @JoinColumn(name = "movieId")
    private KMDbMovieInfo kmdbMovieInfo;

    @Comment("줄거리 언어")
    @Column(name = "plot_lang", length = 20)
    private String plotLang;

    @Comment("줄거리 내용")
    @Column(name = "plot_text", length = 5000)
    private String plotText;

    @Builder
    public KMDbMoviePlots(KMDbMovieInfo kmdbMovieInfo, String plotLang, String plotText) {
        this.kmdbMovieInfo = kmdbMovieInfo;
        this.plotLang = plotLang;
        this.plotText = plotText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KMDbMoviePlots kmdbMoviePlots)) return false;
        return plotId != null && plotId.equals(kmdbMoviePlots.plotId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plotId);
    }
}
