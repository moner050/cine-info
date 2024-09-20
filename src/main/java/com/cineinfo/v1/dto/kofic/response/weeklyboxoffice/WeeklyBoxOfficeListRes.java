package com.cineinfo.v1.dto.kofic.response.weeklyboxoffice;

import com.cineinfo.v1.domain.kmdb.KMDbMovieInfo;
import com.cineinfo.v1.domain.kofic.KOFICWeeklyBoxOffice;
import com.cineinfo.v1.domain.kofic.KOFICWeeklyBoxOfficeId;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WeeklyBoxOfficeListRes {

    private String rnum;
    private String rank;
    private String rankInten;
    private String rankOldAndNew;
    private String movieCd;
    private String movieNm;
    private String openDt;
    private String salesAmt;
    private String salesShare;
    private String salesInten;
    private String salesChange;
    private String salesAcc;
    private String audiCnt;
    private String audiInten;
    private String audiChange;
    private String audiAcc;
    private String scrnCnt;
    private String showCnt;

    public KOFICWeeklyBoxOffice toEntity(String repNationCd, String startDate, String endDate, KMDbMovieInfo kmDbMovieInfo) {
        KOFICWeeklyBoxOfficeId koficWeeklyBoxOfficeId = new KOFICWeeklyBoxOfficeId(movieNm, LocalDate.parse(openDt, DateTimeFormatter.ofPattern("yyyy-MM-dd")), repNationCd);
        return KOFICWeeklyBoxOffice.builder()
                .koficWeeklyBoxOfficeId(koficWeeklyBoxOfficeId)
                .movieRank(Integer.parseInt(rank))
                .rankOldAndNew(rankOldAndNew)
                .kmdbMovieInfo(kmDbMovieInfo)
                .salesAmt(Long.parseLong(salesAmt))
                .salesShare(Double.parseDouble(salesShare))
                .salesInten(Long.parseLong(salesInten))
                .salesChange(Double.parseDouble(salesChange))
                .salesAcc(Long.parseLong(salesAcc))
                .audiCnt(Long.parseLong(audiCnt))
                .audiInten(Long.parseLong(audiInten))
                .audiChange(Double.parseDouble(audiChange))
                .audiAcc(Long.parseLong(audiAcc))
                .scrnCnt(Integer.parseInt(scrnCnt))
                .showCnt(Integer.parseInt(showCnt))
                .startDateRange(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd")))
                .endDateRange(LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyyMMdd")))
                .build();
    }

    public KOFICWeeklyBoxOffice toEntity(String repNationCd, String startDate, String endDate) {
        KOFICWeeklyBoxOfficeId koficWeeklyBoxOfficeId = new KOFICWeeklyBoxOfficeId(movieNm, LocalDate.parse(openDt, DateTimeFormatter.ofPattern("yyyy-MM-dd")), repNationCd);
        return KOFICWeeklyBoxOffice.builder()
                .koficWeeklyBoxOfficeId(koficWeeklyBoxOfficeId)
                .movieRank(Integer.parseInt(rank))
                .rankOldAndNew(rankOldAndNew)
                .salesAmt(Long.parseLong(salesAmt))
                .salesShare(Double.parseDouble(salesShare))
                .salesInten(Long.parseLong(salesInten))
                .salesChange(Double.parseDouble(salesChange))
                .salesAcc(Long.parseLong(salesAcc))
                .audiCnt(Long.parseLong(audiCnt))
                .audiInten(Long.parseLong(audiInten))
                .audiChange(Double.parseDouble(audiChange))
                .audiAcc(Long.parseLong(audiAcc))
                .scrnCnt(Integer.parseInt(scrnCnt))
                .showCnt(Integer.parseInt(showCnt))
                .startDateRange(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyyMMdd")))
                .endDateRange(LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyyMMdd")))
                .build();
    }
}
