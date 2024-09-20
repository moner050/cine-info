package com.cineinfo.v1.repository.kofic;

import com.cineinfo.v1.domain.kofic.KOFICDailyBoxOffice;
import com.cineinfo.v1.domain.kofic.KOFICDailyBoxOfficeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface KOFICDailyBoxOfficeRepository extends JpaRepository<KOFICDailyBoxOffice, KOFICDailyBoxOfficeId> {

    List<KOFICDailyBoxOffice> findByKmdbMovieInfo_MovieIdIsNull();
    List<KOFICDailyBoxOffice> findByKoficDailyBoxOfficeId_RepNationCd(String repNationCd);
    List<KOFICDailyBoxOffice> findByKoficDailyBoxOfficeId_RepNationCdAndTargetDate(String repNationCd, LocalDate targetDate);
}
