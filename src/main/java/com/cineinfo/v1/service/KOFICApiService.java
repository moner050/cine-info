package com.cineinfo.v1.service;

import com.cineinfo.v1.client.KOFICClient;
import com.cineinfo.v1.domain.kofic.KOFICComCode;
import com.cineinfo.v1.domain.kofic.KOFICCompanyInfo;
import com.cineinfo.v1.domain.kofic.KOFICMovieInfo;
import com.cineinfo.v1.dto.kofic.response.SearchKOFICCodeListRes;
import com.cineinfo.v1.dto.kofic.response.SearchKOFICCompanyListRes;
import com.cineinfo.v1.dto.kofic.response.SearchKOFICMovieListRes;
import com.cineinfo.v1.dto.kofic.response.comcode.CodesRes;
import com.cineinfo.v1.dto.kofic.response.company_list.CompanyListRes;
import com.cineinfo.v1.dto.kofic.response.movie_list.MovieListRes;
import com.cineinfo.v1.repository.kofic.*;
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
    private final KOFICCompanyInfoRepository koficCompanyInfoRepository;
    private final KOFICCompanyFilmoRepository koficCompanyFilmoRepository;
    private final KOFICMovieInfoRepository koficMovieInfoRepository;
    private final KOFICMoviePeopleRepository koficMoviePeopleRepository;
    private final KOFICPeopleFilmoRepository koficPeopleFilmoRepository;

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

    // 영화 리스트 저장
    @Transactional
    public boolean saveMovieList(String curPage, String openStartDt) {
        int count = 0;
        //! 테스트 끝나면 모든 데이터 넣어줄 예정.
//        int totCnt = getMovieListTotCnt(openStartDt);

//        for (int i = 1; i <= totCnt; i++) {
            SearchKOFICMovieListRes searchMovieList = koficClient.searchMovieList(curPage, openStartDt);
            List<MovieListRes> movieListRes = searchMovieList.getMovieListResult().getMovieList();

            for (MovieListRes movie : movieListRes) {
                KOFICMovieInfo entity = MovieListRes.toEntity(movie);

                if (!koficMovieInfoRepository.existsById(entity.getMovieCd())) {
                    koficMovieInfoRepository.save(entity);
                    count++;
                }
            }
//        }
        log.info(count + " 개 저장 완료.");
        return true;
    }

    // 영화사 리스트 저장
    @Transactional
    public boolean saveCompanyList(String curPage) {
        int count = 0;

        SearchKOFICCompanyListRes searchCompanyList = koficClient.searchCompanyList(curPage);
        List<CompanyListRes> companyList = searchCompanyList.getCompanyListResult().getCompanyList();

        for (CompanyListRes company : companyList) {
            KOFICCompanyInfo entity = CompanyListRes.toEntity(company);

            if(!koficCompanyInfoRepository.existsById(entity.getCompanyCd())) {
                koficCompanyInfoRepository.save(entity);
                count++;
            }
        }

        log.info(count + " 개 저장 완료.");
        return true;
    }


    public int getMovieListTotCnt(String openStartDt) {
        SearchKOFICMovieListRes searchMovieList = koficClient.searchMovieList("1", openStartDt);
        return searchMovieList.getMovieListResult().getTotCnt();
    }

    public int getCompanyListTotCnt() {
        SearchKOFICCompanyListRes searchCompanyList = koficClient.searchCompanyList("1");
        return searchCompanyList.getCompanyListResult().getTotCnt();
    }
}
