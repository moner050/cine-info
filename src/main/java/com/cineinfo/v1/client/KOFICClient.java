package com.cineinfo.v1.client;

import com.cineinfo.v1.dto.kofic.response.SearchMovieListRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Component
public class KOFICClient {

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


    // 영화 목록 조회
    public SearchMovieListRes searchMovieList(String curPage, String openStartDt) {
        URI uri = UriComponentsBuilder.fromUriString(prefixUrl + movieList)
                .queryParam("key", koficKey)
                .queryParam("curPage", curPage)
                .queryParam("itemPerPage", 100)
                .queryParam("openStartDt", openStartDt)
                .build().encode().toUri();

        log.info("uri : " + uri.toString());

        // header 에 api 키값을 더해준다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // responseType 을 SearchMovieListRes 로 담아준다.
        ParameterizedTypeReference<SearchMovieListRes> responseType = new ParameterizedTypeReference<SearchMovieListRes>() {};

        // httpEntity 에 headers 의 내용을 담아준다.
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        // RestTemplate 로 결과값을 받아온다.
        ResponseEntity<SearchMovieListRes> searchMovieListRestTemplate = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return searchMovieListRestTemplate.getBody();
    }
}
