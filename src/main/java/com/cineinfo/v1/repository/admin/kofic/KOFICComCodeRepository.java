package com.cineinfo.v1.repository.admin.kofic;

import com.cineinfo.v1.domain.admin.kofic.KOFICComCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KOFICComCodeRepository extends JpaRepository<KOFICComCode, String> {

    List<KOFICComCode> findBySummaryCd (String summaryCd);
}
