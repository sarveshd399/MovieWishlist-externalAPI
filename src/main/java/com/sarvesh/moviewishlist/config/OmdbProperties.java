package com.sarvesh.moviewishlist.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "omdb.api")
@Data
public class OmdbProperties {
    private String key;
    private String baseUrl;
}
