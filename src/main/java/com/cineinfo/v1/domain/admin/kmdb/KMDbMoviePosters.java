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
@Comment("KMDb 영화 포스터 URL 링크")
@Entity(name = "kmdb_movie_posters")
public class KMDbMoviePosters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poster_id")
    private Long posterId;

    @Comment("영화 아이디")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "movieId")
    private KMDbMovieInfo kmdbMovieInfo;

    @Comment("영화 포스터 url")
    @Column(name = "url", length = 2048)
    private String url;

    @Builder
    public KMDbMoviePosters(KMDbMovieInfo kmdbMovieInfo, String url) {
        this.kmdbMovieInfo = kmdbMovieInfo;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KMDbMoviePosters kmdbMoviePosters)) return false;
        return posterId != null && posterId.equals(kmdbMoviePosters.posterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(posterId);
    }
}
