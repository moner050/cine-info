package com.cineinfo.v1.service;

import com.cineinfo.v1.client.KOFICClient;
import com.cineinfo.v1.domain.kofic.KOFICComCode;
import com.cineinfo.v1.dto.kofic.response.SearchKOFICCodeListRes;
import com.cineinfo.v1.dto.kofic.response.comcode.CodesRes;
import com.cineinfo.v1.repository.kofic.KOFICComCodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class KOFICApiService {

    private final KOFICClient koficClient;
    private final KOFICComCodeRepository koficComCodeRepository;

    // 공통 코드 저장
    @Transactional
    public boolean saveComCode(String summary) {
        int count = 0;
        SearchKOFICCodeListRes searchCodeList = koficClient.searchCodeList(summary);
        List<CodesRes> codes = searchCodeList.getCodes();

        for (CodesRes code : codes) {
            KOFICComCode entity = CodesRes.toEntity(code, summary);

            if (!koficComCodeRepository.existsById(entity.getFullCd())) {
                koficComCodeRepository.save(entity);
                count++;
            }
        }

        log.info(count + " 개 저장 완료.");
        return true;
    }

}
