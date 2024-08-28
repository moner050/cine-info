package com.cineinfo.v1.repository.kofic;

import com.cineinfo.v1.domain.kofic.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, String> {
}
