package com.cineinfo.v1.repository.kofic;

import com.cineinfo.v1.domain.kofic.KOFICDailyBoxOffice;
import com.cineinfo.v1.domain.kofic.KOFICDailyBoxOfficeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface KOFICDailyBoxOfficeRepository extends JpaRepository<KOFICDailyBoxOffice, KOFICDailyBoxOfficeId> {

    boolean existsByKoficDailyBoxOfficeId_MovieNmAndKoficDailyBoxOfficeId_OpenDtAndKoficDailyBoxOfficeId_RepNationCd(String movieNm, LocalDate openDt, String repNationCd);
    Optional<KOFICDailyBoxOffice> findByKoficDailyBoxOfficeId_RepNationCdAndMovieRankAndTargetDate(String repNationCd, Integer movieRank, LocalDate targetDate);
    List<KOFICDailyBoxOffice> findByKmdbMovieInfo_MovieIdIsNull();
    List<KOFICDailyBoxOffice> findByKoficDailyBoxOfficeId_RepNationCd(String repNationCd);
    List<KOFICDailyBoxOffice> findByKoficDailyBoxOfficeId_RepNationCdAndTargetDate(String repNationCd, LocalDate targetDate);
}
