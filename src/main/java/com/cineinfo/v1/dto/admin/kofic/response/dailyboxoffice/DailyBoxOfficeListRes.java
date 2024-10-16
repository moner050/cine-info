package com.cineinfo.v1.dto.admin.kofic.response.dailyboxoffice;

import com.cineinfo.v1.domain.admin.kmdb.KMDbMovieInfo;
import com.cineinfo.v1.domain.admin.kofic.KOFICDailyBoxOffice;
import com.cineinfo.v1.domain.admin.kofic.KOFICDailyBoxOfficeId;
import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DailyBoxOfficeListRes {

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

    public KOFICDailyBoxOffice toEntity(String repNationCd, String targetDate, KMDbMovieInfo kmDbMovieInfo) {
        LocalDate parseOpenDt = LocalDate.parse(openDt, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String parseRepNationCd = repNationCd.isBlank() ? "A" : repNationCd;
        LocalDate parseTargetDate = LocalDate.parse(targetDate, DateTimeFormatter.ofPattern("yyyyMMdd"));

        KOFICDailyBoxOfficeId koficDailyBoxOfficeId = new KOFICDailyBoxOfficeId(movieNm, parseOpenDt, parseRepNationCd, parseTargetDate);
        return KOFICDailyBoxOffice.builder()
                .koficDailyBoxOfficeId(koficDailyBoxOfficeId)
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
                .build();
    }

    public KOFICDailyBoxOffice toEntity(String repNationCd, String targetDate) {
        LocalDate parseOpenDt = LocalDate.parse(openDt, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String parseRepNationCd = repNationCd.isBlank() ? "A" : repNationCd;
        LocalDate parseTargetDate = LocalDate.parse(targetDate, DateTimeFormatter.ofPattern("yyyyMMdd"));

        KOFICDailyBoxOfficeId koficDailyBoxOfficeId = new KOFICDailyBoxOfficeId(movieNm, parseOpenDt, parseRepNationCd, parseTargetDate);
        return KOFICDailyBoxOffice.builder()
                .koficDailyBoxOfficeId(koficDailyBoxOfficeId)
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
                .build();
    }
}
