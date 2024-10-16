package com.cineinfo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("테스트 도구 - FormDataEncoder")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = {FormDataEncoder.class, ObjectMapper.class})
public class FormDataEncoderTest {

    private final FormDataEncoder formDataEncoder;

    FormDataEncoderTest(@Autowired FormDataEncoder formDataEncoder) {
        this.formDataEncoder = formDataEncoder;
    }

    @DisplayName("객체를 넣으면, url encoding 된 form body data 형식의 문자열 리턴")
    @Test
    void givenObject_whenEncoding_thenReturnFormEncodedString() {
        // given
        TestObject obj = new TestObject("20240101", "20241015", "F", "0");

        // when
        String result = formDataEncoder.encode(obj);

        // then
        Assertions.assertThat(result).isEqualTo(
                "startDate=20240101&endDate=20241015&repNationCd=F&week=0"
        );
    }

    @DisplayName("객체를 넣으면, JSON 형식의 문자열 리턴")
    @Test
    void givenObject_whenEncodingJson_thenReturnJsonEncodedString() {
        // given
        TestObject obj = new TestObject("20240101", "20241015", "F", "0");

        // when
        String result = formDataEncoder.encodeToJson(obj);

        // then
        Assertions.assertThat(result).isEqualTo(
                "{\"startDate\":\"20240101\",\"endDate\":\"20241015\",\"repNationCd\":\"F\",\"week\":\"0\"}"
        );
    }

}

record TestObject(String startDate, String endDate, String repNationCd, String week) {

}