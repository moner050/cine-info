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

    public <T> T getSearchResponse(ParameterizedTypeReference<T> responseTypeReference, MultiValueMap<String, String> multiValueMap, String url, RestTemplate restTemplate) {
        URI uri = UriComponentsBuilder.fromUriString(url)
                .queryParams(multiValueMap)
                .build().encode().toUri();

        log.info("URI: " + uri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // httpEntity 에 headers 의 내용을 담아준다.
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);

        // RestTemplate 로 결과값을 받아온다.
        ResponseEntity<T> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, responseTypeReference);

        return responseEntity.getBody();
    }

}
