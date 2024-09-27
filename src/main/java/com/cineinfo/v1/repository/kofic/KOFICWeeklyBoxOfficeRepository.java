package com.cineinfo.v1.repository.kofic;

import com.cineinfo.v1.domain.kofic.KOFICWeeklyBoxOffice;
import com.cineinfo.v1.domain.kofic.KOFICWeeklyBoxOfficeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface KOFICWeeklyBoxOfficeRepository extends JpaRepository<KOFICWeeklyBoxOffice, KOFICWeeklyBoxOfficeId> {

    boolean existsByKoficWeeklyBoxOfficeId_MovieNmAndKoficWeeklyBoxOfficeId_OpenDtAndKoficWeeklyBoxOfficeId_RepNationCdAndStartDateRange(String movieNm, LocalDate openDt, String repNationCd, LocalDate startDateRange);
    Optional<KOFICWeeklyBoxOffice> findByKoficWeeklyBoxOfficeId_RepNationCdAndMovieRankAndStartDateRange(String repNationCd, Integer movieRank, LocalDate startDateRange);
    List<KOFICWeeklyBoxOffice> findByKmdbMovieInfo_MovieIdIsNull();
    List<KOFICWeeklyBoxOffice> findByKoficWeeklyBoxOfficeId_RepNationCd(String repNationCd);
    List<KOFICWeeklyBoxOffice> findByKoficWeeklyBoxOfficeId_RepNationCdAndStartDateRange(String repNationCd, LocalDate startDateRange);
}
