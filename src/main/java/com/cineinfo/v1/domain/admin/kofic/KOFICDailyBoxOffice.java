package com.cineinfo.v1.domain.admin.kofic;

import com.cineinfo.v1.domain.admin.kmdb.KMDbMovieInfo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Comment("일간 박스오피스 순위")
@Entity(name = "kofic_daily_boxoffice")
public class KOFICDailyBoxOffice {

    @EmbeddedId
    KOFICDailyBoxOfficeId koficDailyBoxOfficeId;

    @Comment("영화 순위")
    @Column(name = "movie_rank", length = 2, nullable = false)
    private Integer movieRank;

    @Comment("신규 진입 여부")
    @Column(name = "rank_old_and_new", length = 3, nullable = false)
    private String rankOldAndNew;

    @Comment("영화 아이디")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private KMDbMovieInfo kmdbMovieInfo;

    @Comment("해당일 매출액")
    @Column(name = "sales_amt")
    private Long salesAmt;

    @Comment("해당일 매출총액 대비 매출비율")
    @Column(name = "sales_share")
    private Double salesShare;

    @Comment("전일 대비 매출액 증감분")
    @Column(name = "sales_inten")
    private Long salesInten;

    @Comment("전일 대비 매출액 증감 비율")
    @Column(name = "sales_change")
    private Double salesChange;

    @Comment("누적 매출액")
    @Column(name = "sales_acc")
    private Long salesAcc;

    @Comment("해당일 관객수")
    @Column(name = "audi_cnt")
    private Long audiCnt;

    @Comment("전일 대비 관객수 증감분")
    @Column(name = "audi_inten")
    private Long audiInten;

    @Comment("전일 대비 관객수 증감 비율")
    @Column(name = "audi_change")
    private Double audiChange;

    @Comment("누적관객수")
    @Column(name = "audi_acc")
    private Long audiAcc;

    @Comment("해당일자에 상영한 스크린수")
    @Column(name = "scrn_cnt")
    private Integer scrnCnt;

    @Comment("해당일자에 상영된 횟수")
    @Column(name = "show_cnt")
    private Integer showCnt;

    @Builder
    public KOFICDailyBoxOffice(KOFICDailyBoxOfficeId koficDailyBoxOfficeId, Integer movieRank, String rankOldAndNew, KMDbMovieInfo kmdbMovieInfo, Long salesAmt, Double salesShare, Long salesInten, Double salesChange, Long salesAcc, Long audiCnt, Long audiInten, Double audiChange, Long audiAcc, Integer scrnCnt, Integer showCnt) {
        this.koficDailyBoxOfficeId = koficDailyBoxOfficeId;
        this.movieRank = movieRank;
        this.rankOldAndNew = rankOldAndNew;
        this.kmdbMovieInfo = kmdbMovieInfo;
        this.salesAmt = salesAmt;
        this.salesShare = salesShare;
        this.salesInten = salesInten;
        this.salesChange = salesChange;
        this.salesAcc = salesAcc;
        this.audiCnt = audiCnt;
        this.audiInten = audiInten;
        this.audiChange = audiChange;
        this.audiAcc = audiAcc;
        this.scrnCnt = scrnCnt;
        this.showCnt = showCnt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KOFICDailyBoxOffice koficDailyBoxOffice)) return false;
        return koficDailyBoxOfficeId != null && koficDailyBoxOfficeId.equals(koficDailyBoxOffice.koficDailyBoxOfficeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(koficDailyBoxOfficeId);
    }
}
