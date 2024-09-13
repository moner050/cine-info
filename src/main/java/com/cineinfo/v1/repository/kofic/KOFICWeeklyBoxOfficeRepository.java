package com.cineinfo.v1.repository.kofic;

import com.cineinfo.v1.domain.kofic.KOFICWeeklyBoxOffice;
import com.cineinfo.v1.domain.kofic.KOFICWeeklyBoxOfficeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KOFICWeeklyBoxOfficeRepository extends JpaRepository<KOFICWeeklyBoxOffice, KOFICWeeklyBoxOfficeId> {
}
