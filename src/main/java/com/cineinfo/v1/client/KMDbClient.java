package com.cineinfo.v1.client;

import com.cineinfo.v1.dto.kmdb.request.SearchKMDbMovieListReq;
import com.cineinfo.v1.dto.kmdb.response.SearchKMDbMovieListRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class KMDbClient {

    private final RestTemplate restTemplate;
    private final String serviceKey;                        // API 서비스 인증키
    private final String basicUrl;                          // 기본요청 URL

    /******************************** application-kmdb 에 설정된 값 불러오기 ************************************/
    public KMDbClient(RestTemplate restTemplate,
                      @Value("${kmdb.serviceKey}") String key,
                      @Value("${kmdb.url}") String url
                      ) {
        this.restTemplate = restTemplate;
        this.serviceKey = key;
        this.basicUrl = url;
    }
    /********************************************************************************************************/

    // 영화 상세 정보 검색
    public SearchKMDbMovieListRes searchKMDbMovieList (String startCount, String listCount, String releaseDts, String releaseDte) {
        RestTemplateClient restTemplateClient = new RestTemplateClient();
        SearchKMDbMovieListReq searchKMDbMovieList = new SearchKMDbMovieListReq(serviceKey, listCount, startCount, "kmdb_new2", "Y", "prodYear,0", releaseDts, releaseDte, "y");

        ParameterizedTypeReference<SearchKMDbMovieListRes> responseType = new ParameterizedTypeReference<SearchKMDbMovieListRes>() {};

        return restTemplateClient.getSearchResponse(responseType, searchKMDbMovieList.toMultiValueMap(), (basicUrl), restTemplate);
    }

}
