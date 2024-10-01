package com.cineinfo.config;

import com.cineinfo.v1.interceptor.ResponseBodyModifierInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
@ComponentScan(basePackages = "com.cineinfo.v1.client")
public class RestClientConfig {

    @Bean
    public RestClient restClient() {
        // 인터셉터 설정
        ResponseBodyModifierInterceptor interceptor = new ResponseBodyModifierInterceptor();

        return RestClient.builder()
                .requestInterceptor(interceptor)
                .build();
    }
}
