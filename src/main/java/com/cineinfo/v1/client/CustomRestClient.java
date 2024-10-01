package com.cineinfo.v1.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
public class CustomRestClient {

    public <T> T getSearchResponse(Class<T> clazz, MultiValueMap<String, String> multiValueMap, String baseUrl, String pathUrl, RestClient restClient) {

        URI uri = UriComponentsBuilder.fromUriString(baseUrl + pathUrl)
                .queryParams(multiValueMap)
                .build().encode().toUri();

        log.info("url: " + uri);

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(clazz);
    }
}
