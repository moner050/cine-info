package com.cineinfo.v1.repository;

import com.cineinfo.v1.domain.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, String> {
}
