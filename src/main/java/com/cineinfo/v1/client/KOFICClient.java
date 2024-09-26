package com.cineinfo.v1.client;

import com.cineinfo.v1.dto.kofic.request.SearchKOFICCodeListReq;
import com.cineinfo.v1.dto.kofic.request.SearchKOFICDailyBoxOfficeReq;
import com.cineinfo.v1.dto.kofic.request.SearchKOFICWeeklyBoxOfficeReq;
import com.cineinfo.v1.dto.kofic.response.SearchKOFICCodeListRes;
import com.cineinfo.v1.dto.kofic.response.SearchKOFICDailyBoxOfficeRes;
import com.cineinfo.v1.dto.kofic.response.SearchKOFICWeeklyBoxOfficeRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class KOFICClient {

    private final RestTemplate restTemplate;
    private final String koficKey;                      //  키값
    private final String subKey;                        //  임시 키값
    private final String prefixUrl;                     //  기본주소
    private final String codeList;                      //  공통코드
    private final String movieList;                     //  영화목록
    private final String movieInfo;                     //  영화 상세정보
    private final String companyList;                   //  영화사목록
    private final String companyInfo;                   //  영화사 상세정보
    private final String peopleList;                    //  영화인목록
    private final String peopleInfo;                    //  영화인 상세정보
    private final String dailyBoxOfficeList;            //  일별 박스오피스
    private final String weeklyBoxOfficeList;           //  주간/주말 박스오피스

    /******************************** application-kofic 에 설정된 값 불러오기 ************************************/
    public KOFICClient(RestTemplate restTemplate,
                       @Value("${kofic.key1}") String key1,
                       @Value("${kofic.key2}") String key2,
                       @Value("${kofic.url.prefix}") String prefix,
                       @Value("${kofic.url.searchCodeList}") String searchCodeList,
                       @Value("${kofic.url.searchMovieList}") String searchMovieList,
                       @Value("${kofic.url.searchMovieInfo}") String searchMovieInfo,
                       @Value("${kofic.url.searchCompanyList}") String searchCompanyList,
                       @Value("${kofic.url.searchCompanyInfo}") String searchCompanyInfo,
                       @Value("${kofic.url.searchPeopleList}") String searchPeopleList,
                       @Value("${kofic.url.searchPeopleInfo}") String searchPeopleInfo,
                       @Value("${kofic.url.searchDailyBoxOfficeList}") String searchDailyBoxOfficeList,
                       @Value("${kofic.url.searchWeeklyBoxOfficeList}") String searchWeeklyBoxOfficeList
                       ) {
        this.restTemplate = restTemplate;
        this.koficKey = key1;
        this.subKey = key2;
        this.prefixUrl = prefix;
        this.codeList = searchCodeList;
        this.movieList = searchMovieList;
        this.movieInfo = searchMovieInfo;
        this.companyList = searchCompanyList;
        this.companyInfo = searchCompanyInfo;
        this.peopleList = searchPeopleList;
        this.peopleInfo = searchPeopleInfo;
        this.dailyBoxOfficeList = searchDailyBoxOfficeList;
        this.weeklyBoxOfficeList = searchWeeklyBoxOfficeList;
    }

    // 공통 코드 조회
    public SearchKOFICCodeListRes searchCodeList(String comCode) {
        RestTemplateClient restTemplateClient = new RestTemplateClient();
        SearchKOFICCodeListReq searchCodeListReq = new SearchKOFICCodeListReq(koficKey, comCode);

        ParameterizedTypeReference<SearchKOFICCodeListRes> responseType = new ParameterizedTypeReference<SearchKOFICCodeListRes>() {};

        return restTemplateClient.getSearchResponse(responseType, searchCodeListReq.toMultiValueMap(), (prefixUrl + codeList), restTemplate);
    }


    // 일간 박스오피스 순위 조회
    public SearchKOFICDailyBoxOfficeRes searchDailyBoxOffice(String targetDt, String repNationCd) {
        RestTemplateClient restTemplateClient = new RestTemplateClient();
        SearchKOFICDailyBoxOfficeReq searchKOFICDailyBoxOfficeReq = new SearchKOFICDailyBoxOfficeReq(koficKey, targetDt, repNationCd);

        ParameterizedTypeReference<SearchKOFICDailyBoxOfficeRes> responseType = new ParameterizedTypeReference<SearchKOFICDailyBoxOfficeRes>() {};

        return restTemplateClient.getSearchResponse(responseType, searchKOFICDailyBoxOfficeReq.toMultiValueMap(), (prefixUrl + dailyBoxOfficeList), restTemplate);
    }

    // 주간 박스오피스 순위 조회
    public SearchKOFICWeeklyBoxOfficeRes searchWeeklyBoxOffice(String targetDt, String repNationCd, String weekGb) {
        RestTemplateClient restTemplateClient = new RestTemplateClient();
        SearchKOFICWeeklyBoxOfficeReq searchWeeklyBoxOfficeReq = new SearchKOFICWeeklyBoxOfficeReq(koficKey, targetDt, repNationCd, weekGb);

        ParameterizedTypeReference<SearchKOFICWeeklyBoxOfficeRes> responseType = new ParameterizedTypeReference<SearchKOFICWeeklyBoxOfficeRes>() {};

        return restTemplateClient.getSearchResponse(responseType, searchWeeklyBoxOfficeReq.toMultiValueMap(), (prefixUrl + weeklyBoxOfficeList), restTemplate);
    }
}
