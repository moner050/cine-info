package com.cineinfo.v1.repository.kofic;

import com.cineinfo.v1.domain.kofic.KOFICDailyBoxOffice;
import com.cineinfo.v1.domain.kofic.KOFICDailyBoxOfficeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KOFICDailyBoxOfficeRepository extends JpaRepository<KOFICDailyBoxOffice, KOFICDailyBoxOfficeId> {
}
