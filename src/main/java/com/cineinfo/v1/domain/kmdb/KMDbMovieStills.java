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
@Comment("KMDb 영화 스틸컷 URL 링크")
@Entity(name = "kmdb_movie_stills")
public class KMDbMovieStills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "still_id")
    private Long stillId;

    @Comment("영화 아이디")
    @ManyToOne(optional = false)
    @JoinColumn(name = "movieId")
    private KMDbMovieInfo kmdbMovieInfo;

    @Comment("영화 스틸컷 url")
    @Column(name = "url", length = 2048)
    private String url;

    @Builder
    public KMDbMovieStills(KMDbMovieInfo kmdbMovieInfo, String url) {
        this.kmdbMovieInfo = kmdbMovieInfo;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KMDbMovieStills kmdbMovieStills)) return false;
        return stillId != null && stillId.equals(kmdbMovieStills.stillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stillId);
    }
}
