package com.cineinfo.v1.service;

import com.cineinfo.v1.client.KMDbClient;
import com.cineinfo.v1.domain.kmdb.KMDbMovieInfo;
import com.cineinfo.v1.domain.kmdb.KMDbMoviePosters;
import com.cineinfo.v1.dto.kmdb.response.SearchKMDbMovieListRes;
import com.cineinfo.v1.dto.kmdb.response.movie_list.DataRes;
import com.cineinfo.v1.dto.kmdb.response.movie_list.ResultRes;
import com.cineinfo.v1.repository.kmdb.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class KMDbApiService {

    private final KMDbClient kmdbClient;
    private final KMDbMovieInfoRepository kmdbMovieInfoRepository;
    private final KMDbMoviePlotsRepository kmdbMoviePlotsRepository;
    private final KMDbMoviePostersRepository kmdbMoviePostersRepository;
    private final KMDbMovieStaffsRepository kmdbMovieStaffsRepository;
    private final KMDbMovieStillsRepository kmdbMovieStillsRepository;
    private final KMDbMovieVodsRepository kmdbMovieVodsRepository;

    // 영화 상세 정보 검색 및 저장
    @Transactional
    public boolean searchKMDbMovieList (String startCount, String listCount, String releaseDts, String releaseDte) {
        SearchKMDbMovieListRes searchKMDbMovieList = kmdbClient.searchKMDbMovieList(startCount, listCount, releaseDts, releaseDte);

        log.info("TotalCount: " + searchKMDbMovieList.getTotalCount());
        log.info("data size: " + searchKMDbMovieList.getData().size());

        DataRes dataRes = searchKMDbMovieList.getData().get(0);

        log.info("TotalCount: " + dataRes.getTotalCount());
        log.info("Count: " + dataRes.getCount());

        for (ResultRes resultRes : dataRes.getResult()) {

            // 해당 영화 정보가 이미 있으면 건너뛰기
            if(kmdbMovieInfoRepository.existsById(resultRes.getDOCID())) {
                continue;
            }

            // KMDb 영화 정보 저장
            KMDbMovieInfo savedEntity = kmdbMovieInfoRepository.save(resultRes.toEntity());

            // KMDb 영화 줄거리 저장
            int plotsListSize = resultRes.getPlots().getPlot().size();
            for (int i = 0; i < plotsListSize; i++) {
                log.info("plot count : " + (i+1));
                kmdbMoviePlotsRepository.save(resultRes.getPlots().getPlot().get(i).toEntity(savedEntity));
            }

            // KMDb 영화 스태프 정보 저장
            int staffsListSize = resultRes.getStaffs().getStaff().size();
            for (int i = 0; i < staffsListSize; i++) {
                log.info("staff count : " + (i+1));
                kmdbMovieStaffsRepository.save(resultRes.getStaffs().getStaff().get(i).toEntity(savedEntity));
            }

            String[] posters = resultRes.getPosters().split("\\|");
            String[] stills = resultRes.getStlls().split("\\|");

            // KMDb 영화 포스터 URL 링크 정보 저장
            for (String url : posters) {
                log.info("poster url : " + url);
                kmdbMoviePostersRepository.save(resultRes.toPosterEntity(savedEntity, url));
            }

            // KMDb 영화 스틸컷 URL 링크 정보 저장
            for (String url : stills) {
                log.info("stills url : " + url);
                kmdbMovieStillsRepository.save(resultRes.toStillsEntity(savedEntity, url));
            }

            // KMDb 영화 VOD 정보 저장
            int vodsListSize = resultRes.getVods().getVod().size();
            for (int i = 0; i < vodsListSize; i++) {
                log.info("vod count : " + (i+1));
                kmdbMovieVodsRepository.save(resultRes.getVods().getVod().get(i).toEntity(savedEntity));
            }

        }

        return true;
    }
}
