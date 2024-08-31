package com.cineinfo.v1.repository.kofic;

import com.cineinfo.v1.domain.kofic.ComCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComCodeRepository extends JpaRepository<ComCode, String> {

    List<ComCode> findBySummaryCd (String summaryCd);
}
