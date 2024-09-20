package com.cineinfo.v1.repository.kofic;

import com.cineinfo.v1.domain.kofic.KOFICWeeklyBoxOffice;
import com.cineinfo.v1.domain.kofic.KOFICWeeklyBoxOfficeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface KOFICWeeklyBoxOfficeRepository extends JpaRepository<KOFICWeeklyBoxOffice, KOFICWeeklyBoxOfficeId> {

    List<KOFICWeeklyBoxOffice> findByKmdbMovieInfo_MovieIdIsNull();
    List<KOFICWeeklyBoxOffice> findByKoficWeeklyBoxOfficeId_RepNationCd(String repNationCd);
    List<KOFICWeeklyBoxOffice> findByKoficWeeklyBoxOfficeId_RepNationCdAndStartDateRange(String repNationCd, LocalDate startDateRange);
}
