package com.cineinfo.v1.dto.kofic.request;


import lombok.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SearchKOFICWeeklyBoxOfficeReq {

    private String key;
    private String targetDt;
    private String repNationCd;
    private String weekGb;

    public MultiValueMap<String, String> toMultiValueMap() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("key", key);
        map.add("targetDt", targetDt);
        map.add("repNationCd", repNationCd);
        map.add("weekGb", weekGb);
        return map;
    }
}
