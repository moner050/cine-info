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

    public KOFICClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /******************************** application-kofic 에 설정된 값 불러오기 ************************************/

    public static String koficKey;                      //  키값
    public static String subKey;                        //  임시 키값
    public static String prefixUrl;                     //  기본주소
    public static String codeList;                      //  공통코드
    public static String movieList;                     //  영화목록
    public static String movieInfo;                     //  영화 상세정보
    public static String companyList;                   //  영화사목록
    public static String companyInfo;                   //  영화사 상세정보
    public static String peopleList;                    //  영화인목록
    public static String peopleInfo;                    //  영화인 상세정보
    public static String dailyBoxOfficeList;            //  일별 박스오피스
    public static String weeklyBoxOfficeList;           //  주간/주말 박스오피스


    @Value("${kofic.key1}")
    public void setKoficKey(String key) {koficKey = key;}
    @Value("${kofic.key2}")
    public void setSubKey(String key) {subKey = key;}
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
