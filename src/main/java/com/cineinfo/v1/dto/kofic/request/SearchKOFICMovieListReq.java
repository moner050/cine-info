package com.cineinfo.v1.dto.kofic.request;

import lombok.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SearchKOFICMovieListReq {

    private String key;
    private String curPage;
    private String itemPerPage;
    private String openStartDt;

    public MultiValueMap<String, String> toMultiValueMap() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("key", key);
        map.add("curPage", curPage);
        map.add("itemPerPage", itemPerPage);
        map.add("openStartDt", openStartDt);
        return map;
    }
}
