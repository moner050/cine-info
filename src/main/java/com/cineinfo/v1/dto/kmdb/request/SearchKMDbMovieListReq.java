package com.cineinfo.v1.dto.kmdb.request;

import lombok.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchKMDbMovieListReq {

    private String serviceKey;              // API 서비스 인증키
    private String listCount;               // 통합검색 출력 결과수
    private String startCount;              // 검색 결과 시작 번호
    private String collection;              // 검색 대상 컬렉션 지정 (kmdb_new2)
    private String detail;                  // 상세정보 출력 여부 (Y or N)
    private String sort;                    // 결과 정렬
    private String releaseDts;              // 개봉일 시작 (YYYYMMDD)
    private String releaseDte;              // 개봉일 종료 (YYYYMMDD)
    private String ratedYn;                 // 심의여부(y/n)

    public MultiValueMap<String, String> toMultiValueMap() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("ServiceKey", serviceKey);
        map.add("listCount", listCount);
        map.add("startCount", startCount);
        map.add("collection", collection);
        map.add("detail", detail);
        map.add("sort", sort);
        map.add("releaseDts", releaseDts);
        map.add("releaseDte", releaseDte);
        map.add("ratedYn", ratedYn);

        return map;
    }
}
