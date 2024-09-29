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
@Comment("KMDb 영화 VOD URL 링크")
@Entity(name = "kmdb_movie_vods")
public class KMDbMovieVods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vod_id")
    private Long vodId;

    @Comment("영화 아이디")
    @ManyToOne(optional = false)
    @JoinColumn(name = "movieId")
    private KMDbMovieInfo kmdbMovieInfo;

    @Comment("영화 VOD 이름")
    @Column(name = "vod_name", length = 500)
    private String vodName;

    @Comment("영화 VOD url")
    @Column(name = "vod_url", length = 2048)
    private String vodUrl;

    @Builder
    public KMDbMovieVods(KMDbMovieInfo kmdbMovieInfo, String vodName, String vodUrl) {
        this.kmdbMovieInfo = kmdbMovieInfo;
        this.vodName = vodName;
        this.vodUrl = vodUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KMDbMovieVods kmdbMovieVods)) return false;
        return vodId != null && vodId.equals(kmdbMovieVods.vodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vodId);
    }
}
