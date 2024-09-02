package com.cineinfo.v1.client;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
public class RestTemplateClient {

    public <T> T getSearchResponse(ParameterizedTypeReference<T> responseTypeReference, MultiValueMap<String, String> multiValueMap, String url, String key, RestTemplate restTemplate) {
        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParam("key", key)
                .queryParams(multiValueMap)
                .build().encode().toUri();

        log.info("URI: " + uri);

        // RestTemplate 로 결과값을 받아온다.
        ResponseEntity<T> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, responseTypeReference);

        return responseEntity.getBody();
    }

}
