package com.cineinfo.v1.service;

import com.cineinfo.v1.client.KMDbClient;
import com.cineinfo.v1.dto.kmdb.response.SearchKMDbMovieListRes;
import com.cineinfo.v1.dto.kmdb.response.movie_list.DataRes;
import com.cineinfo.v1.dto.kmdb.response.movie_list.ResultRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class KMDbApiService {

    private final KMDbClient kmdbClient;

    // 영화 상세 정보 검색
    public boolean searchKMDbMovieList (String startCount, String releaseDts, String releaseDte) {
        SearchKMDbMovieListRes searchKMDbMovieList = kmdbClient.searchKMDbMovieList(startCount, releaseDts, releaseDte);

        log.info("TotalCount: " + searchKMDbMovieList.getTotalCount());
        log.info("data size: " + searchKMDbMovieList.getData().size());

        DataRes dataRes = searchKMDbMovieList.getData().get(0);

        log.info("TotalCount: " + dataRes.getTotalCount());
        log.info("Count: " + dataRes.getCount());

        ResultRes resultRes = dataRes.getResult().get(0);

        log.info("title: " + resultRes.getTitle());
        log.info("rating: " + resultRes.getRating());
        log.info("genre: " + resultRes.getGenre());
        log.info("Awards1: "+ resultRes.getAwards1());
        log.info("Awards2: "+ resultRes.getAwards2());

//        // 총 검색 결과가 500개보다 많으면 나머지 호출
//        if(dataRes.getTotalCount() > 500) {
//            for (long i = 500; i < dataRes.getTotalCount(); i+=500) {
//                searchKMDbMovieList = kmdbClient.searchKMDbMovieList(String.valueOf(i), releaseDts, releaseDte);
//            }
//        }

        return true;
    }
}
