package com.cineinfo.v1.interceptor;

import com.cineinfo.v1.client.ModifiedHttpResponseClient;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResponseBodyModifierInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        // 요청 실행 후, 원본 응답 받기
        ClientHttpResponse response = execution.execute(request, body);

        String originalBody = new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);

        // 응답 Body 특수문자 제거하도록 수정하기
        String modifiedBodyString = originalBody.replaceAll("\\u001A", "");

        byte[] modifiedBody = modifiedBodyString.getBytes(StandardCharsets.UTF_8);

        return new ModifiedHttpResponseClient(response, modifiedBody);
    }
}
