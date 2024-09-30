package com.cineinfo.config;

import com.cineinfo.v1.interceptor.ResponseBodyModifierInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
@ComponentScan(basePackages = "com.cineinfo.v1.client")
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        // 인터셉터 설정
        ResponseBodyModifierInterceptor interceptor = new ResponseBodyModifierInterceptor();

        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(20))
                .setReadTimeout(Duration.ofSeconds(20))
                .interceptors(interceptor)
                .build();
    }
}
