package com.cineinfo.v1.client;

import com.cineinfo.v1.dto.kmdb.request.SearchKMDbMovieListReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class KMDbClient {

    private final RestTemplate restTemplate;

    public KMDbClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static String serviceKey;                        // API 서비스 인증키
    public static String basicUrl;                          // 기본요청 URL

    /******************************** application-kmdb 에 설정된 값 불러오기 ************************************/
    @Value("${kmdb.serviceKey}")
    public void setServiceKey(String key) {
        serviceKey = key;
    }

    @Value("${kmdb.url}")
    public void setBasicUrl(String url) {
        basicUrl = url;
    }

    /********************************************************************************************************/

    // 영화 상세 정보 검색
    public SearchKMDbMovieListRes searchKMDbMovieList (String releaseDts, String releaseDte) {
        RestTemplateClient restTemplateClient = new RestTemplateClient();
        SearchKMDbMovieListReq searchKMDbMovieList = new SearchKMDbMovieListReq("500", "kmdb_new2", "Y", "prodYear", releaseDts, releaseDte, "y");

        ParameterizedTypeReference<SearchKMDbMovieListRes> responseType = new ParameterizedTypeReference<SearchKMDbMovieListRes>() {};

        return restTemplateClient.getSearchResponse(responseType, searchKMDbMovieList.toMultiValueMap(), (basicUrl), serviceKey, restTemplate);
    }

}
