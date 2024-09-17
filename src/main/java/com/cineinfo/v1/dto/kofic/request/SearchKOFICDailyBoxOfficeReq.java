package com.cineinfo.v1.dto.kofic.request;


import lombok.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SearchKOFICDailyBoxOfficeReq {

    private String key;
    private String targetDt;
    private String repNationCd;

    public MultiValueMap<String, String> toMultiValueMap() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("key", key);
        map.add("targetDt", targetDt);
        map.add("repNationCd", repNationCd);
        return map;
    }
}
