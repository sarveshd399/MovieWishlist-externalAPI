package com.sarvesh.moviewishlist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Bean
    public RestClient omdbRestClient(OmdbProperties omdbProperties) {
        return RestClient.builder()
                .baseUrl(omdbProperties.getBaseUrl())
                .build();
    }
}
