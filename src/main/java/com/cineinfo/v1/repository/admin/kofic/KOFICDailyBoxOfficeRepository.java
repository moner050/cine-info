package com.cineinfo.v1.repository.admin.kofic;

import com.cineinfo.v1.domain.admin.kofic.KOFICDailyBoxOffice;
import com.cineinfo.v1.domain.admin.kofic.KOFICDailyBoxOfficeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface KOFICDailyBoxOfficeRepository extends JpaRepository<KOFICDailyBoxOffice, KOFICDailyBoxOfficeId> {

    boolean existsByKoficDailyBoxOfficeId_MovieNmAndKoficDailyBoxOfficeId_OpenDtAndKoficDailyBoxOfficeId_RepNationCdAndKoficDailyBoxOfficeId_TargetDate(String movieNm, LocalDate openDt, String repNationCd, LocalDate targetDate);
    Optional<KOFICDailyBoxOffice> findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndKoficDailyBoxOfficeId_TargetDate(String repNationCd, Integer movieRank, LocalDate targetDate);
    List<KOFICDailyBoxOffice> findByKmdbMovieInfo_MovieIdIsNull();
    List<KOFICDailyBoxOffice> findByKoficDailyBoxOfficeId_RepNationCd(String repNationCd);
    List<KOFICDailyBoxOffice> findByKoficDailyBoxOfficeId_RepNationCdAndKoficDailyBoxOfficeId_TargetDate(String repNationCd, LocalDate targetDate);
    List<KOFICDailyBoxOffice> findByKoficDailyBoxOfficeId_RepNationCdAndKoficDailyBoxOfficeId_TargetDateBetween(String repNationCd, LocalDate startTargetDate, LocalDate endTargetDate);
}
