package com.cineinfo.v1.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ModifiedHttpResponseClient implements ClientHttpResponse {

    private final ClientHttpResponse originalResponse;
    private final byte[] modifiedBody;

    public ModifiedHttpResponseClient(ClientHttpResponse originalResponse, byte[] modifiedBody) {
        this.originalResponse = originalResponse;
        this.modifiedBody = modifiedBody;
    }

    @Override
    public HttpStatusCode getStatusCode() throws IOException {
        return originalResponse.getStatusCode();
    }

    @Override
    public String getStatusText() throws IOException {
        return originalResponse.getStatusText();
    }

    @Override
    public void close() {
        originalResponse.close();
    }

    @Override
    public InputStream getBody() throws IOException {
        return new ByteArrayInputStream(modifiedBody);
    }

    @Override
    public HttpHeaders getHeaders() {
        // 수정한 body 의 길이 및 ContentType 에 맞게 header 수정하기
        HttpHeaders headers = originalResponse.getHeaders();
        headers.setContentLength(modifiedBody.length);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
