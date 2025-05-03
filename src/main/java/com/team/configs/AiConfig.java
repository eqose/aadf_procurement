package com.team.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AiConfig {
    @Bean
    public WebClient aiClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8000")
                .build();
    }
}
