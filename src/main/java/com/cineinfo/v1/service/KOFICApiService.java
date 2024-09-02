package com.cineinfo.v1.service;

import com.cineinfo.v1.client.KOFICClient;
import com.cineinfo.v1.domain.kofic.ComCode;
import com.cineinfo.v1.domain.kofic.MovieInfo;
import com.cineinfo.v1.dto.kofic.response.SearchCodeListRes;
import com.cineinfo.v1.dto.kofic.response.SearchMovieListRes;
import com.cineinfo.v1.dto.kofic.response.comcode.CodesRes;
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
    private final ComCodeRepository comCodeRepository;
    private final CompanyInfoRepository companyInfoRepository;
    private final CompanyFilmoRepository companyFilmoRepository;
    private final MovieInfoRepository movieInfoRepository;
    private final MoviePeopleRepository moviePeopleRepository;
    private final PeopleFilmoRepository peopleFilmoRepository;

    // 공통 코드 저장
    @Transactional
    public boolean saveComCode(String summary) {
        int count = 0;
        SearchCodeListRes searchCodeList = koficClient.searchCodeList(summary);
        List<CodesRes> codes = searchCodeList.getCodes();

        for (CodesRes code : codes) {
            ComCode entity = CodesRes.toEntity(code, summary);

            if (!comCodeRepository.existsById(entity.getFullCd())) {
                comCodeRepository.save(entity);
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
            SearchMovieListRes searchMovieList = koficClient.searchMovieList(curPage, openStartDt);
            List<MovieListRes> movieListRes = searchMovieList.getMovieListResult().getMovieList();

            for (MovieListRes movieListRe : movieListRes) {
                MovieInfo entity = MovieListRes.toEntity(movieListRe);

                if (!movieInfoRepository.existsById(entity.getMovieCd())) {
                    movieInfoRepository.save(entity);
                    count++;
                }
            }
//        }
        log.info(count + " 개 저장 완료.");
        return true;
    }

    public int getMovieListTotCnt(String openStartDt) {
        SearchMovieListRes searchMovieList = koficClient.searchMovieList("1", openStartDt);
        return searchMovieList.getMovieListResult().getTotCnt();
    }
}
