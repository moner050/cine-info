package com.cineinfo.v1.service;

import com.cineinfo.v1.client.KOFICClient;
import com.cineinfo.v1.domain.kofic.MovieInfo;
import com.cineinfo.v1.dto.kofic.response.SearchMovieListRes;
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
    private final MovieCompaniesRepository movieCompaniesRepository;
    private final MoviePeopleRepository moviePeopleRepository;
    private final PeopleFilmoRepository peopleFilmoRepository;

    // 영화 리스트 저장
    @Transactional
    public boolean saveMovieList(String curPage, String openStartDt) {
        SearchMovieListRes searchMovieList = koficClient.searchMovieList(curPage, openStartDt);
        List<MovieListRes> movieListRes = searchMovieList.getMovieListResult().getMovieList();

        Long totCnt = searchMovieList.getMovieListResult().getTotCnt();

        for (int i = 0; i < movieListRes.size(); i++) {
            MovieInfo entity = MovieListRes.toEntity(movieListRes.get(i));

            movieInfoRepository.save(entity);

            log.info(i+1 + " 개 저장 완료. 영화코드: " + entity.getMovieCd() + " directors: " + entity.getDirectorPeopleNms());
        }

        return true;
    }
}
