package com.cineinfo.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ThymeleafConfig {

    // decoupled Logic 세팅 후 리턴해주는 빈 등록
    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver(
            SpringResourceTemplateResolver defaultTemplateResolver,
            Thymeleaf3Properties thymeleaf3Properties
            ) {
        defaultTemplateResolver.setUseDecoupledLogic(thymeleaf3Properties.decoupledLogic());

        return defaultTemplateResolver;
    }

    // 유저가 직접 configuration properties 를 만들었을 경우에 스캔을 해줘야 함.
    @ConfigurationProperties("spring.thymeleaf3")
    public record Thymeleaf3Properties(boolean decoupledLogic) { }
}
