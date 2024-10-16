package com.cineinfo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@TestComponent
public class FormDataEncoder {

    private final ObjectMapper mapper;

    public FormDataEncoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String encode(Object obj) {
        Map<String, String> fieldMap = mapper.convertValue(obj, new TypeReference<Map<String, String>>() {});
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.setAll(fieldMap);

        return UriComponentsBuilder.newInstance()
                .queryParams(valueMap)
                .encode()
                .build()
                .getQuery();
    }

    public String encodeToJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException("객체를 JSON 변환에 실패하였습니다.");
        }
    }
}
