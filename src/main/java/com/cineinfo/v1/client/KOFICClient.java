package com.cineinfo.v1.client;

import com.cineinfo.v1.dto.kofic.request.SearchCodeListReq;
import com.cineinfo.v1.dto.kofic.request.SearchMovieListReq;
import com.cineinfo.v1.dto.kofic.response.SearchCodeListRes;
import com.cineinfo.v1.dto.kofic.response.SearchMovieListRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class KOFICClient {

    private final RestTemplate restTemplate;

    public KOFICClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /******************************** application-kofic 에 설정된 값 불러오기 ************************************/

    public static String koficKey;                      //  키값
    public static String prefixUrl;                     //  기본주소
    public static String codeList;                      //  공통코드
    public static String movieList;                     //  영화목록
    public static String movieInfo;                     //  영화 상세정보
    public static String companyList;                   //  영화사목록
    public static String companyInfo;                   //  영화사 상세정보
    public static String peopleList;                    //  영화인목록
    public static String peopleInfo;                    //  영화인 상세정보
    public static String dailyBoxOfficeList;            //  영화인 상세정보
    public static String weeklyBoxOfficeList;           //  영화인 상세정보


    @Value("${kofic.key1}")
    public void setKoficKey(String key) {
        koficKey = key;}
    @Value("${kofic.url.prefix}")
    public void setPrefixUrl(String prefix) {prefixUrl = prefix;}
    @Value("${kofic.url.searchCodeList}")
    public void setCodeList(String searchCodeList) {codeList = searchCodeList;}
    @Value("${kofic.url.searchMovieList}")
    public void setMovieList(String searchMovieList) {movieList = searchMovieList;}
    @Value("${kofic.url.searchMovieInfo}")
    public void setMovieInfo(String searchMovieInfo) {movieInfo = searchMovieInfo;}
    @Value("${kofic.url.searchCompanyList}")
    public void setCompanyList(String searchCompanyList) {companyList = searchCompanyList;}
    @Value("${kofic.url.searchCompanyInfo}")
    public void setCompanyInfo(String searchCompanyInfo) {companyInfo = searchCompanyInfo;}
    @Value("${kofic.url.searchPeopleList}")
    public void setPeopleList(String searchPeopleList) {peopleList = searchPeopleList;}
    @Value("${kofic.url.searchPeopleInfo}")
    public void setPeopleInfo(String searchPeopleInfo) {peopleInfo = searchPeopleInfo;}
    @Value("${kofic.url.searchDailyBoxOfficeList}")
    public void setDailyBoxOfficeList(String searchDailyBoxOfficeList) {dailyBoxOfficeList = searchDailyBoxOfficeList;}
    @Value("${kofic.url.searchWeeklyBoxOfficeList}")
    public void setWeeklyBoxOfficeList(String searchWeeklyBoxOfficeList) {weeklyBoxOfficeList = searchWeeklyBoxOfficeList;}

    // 공통 코드 조회
    public SearchCodeListRes searchCodeList(String comCode) {
        RestTemplateClient restTemplateClient = new RestTemplateClient();
        SearchCodeListReq searchCodeListReq = new SearchCodeListReq(comCode);

        ParameterizedTypeReference<SearchCodeListRes> responseType = new ParameterizedTypeReference<SearchCodeListRes>() {};

        return restTemplateClient.getSearchResponse(responseType, searchCodeListReq.toMultiValueMap(), (prefixUrl + codeList), koficKey, restTemplate);
    }

    // 영화 목록 조회
    public SearchMovieListRes searchMovieList(String curPage, String openStartDt) {
        RestTemplateClient restTemplateClient = new RestTemplateClient();
        SearchMovieListReq searchMovieListReq = new SearchMovieListReq(curPage, "100", openStartDt);

        ParameterizedTypeReference<SearchMovieListRes> responseType = new ParameterizedTypeReference<SearchMovieListRes>() {};

        return restTemplateClient.getSearchResponse(responseType, searchMovieListReq.toMultiValueMap(), (prefixUrl + movieList), koficKey, restTemplate);
    }

}
