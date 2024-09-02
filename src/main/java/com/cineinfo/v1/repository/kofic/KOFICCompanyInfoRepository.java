package com.cineinfo.v1.repository.kofic;

import com.cineinfo.v1.domain.kofic.KOFICCompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KOFICCompanyInfoRepository extends JpaRepository<KOFICCompanyInfo, String> {
}
