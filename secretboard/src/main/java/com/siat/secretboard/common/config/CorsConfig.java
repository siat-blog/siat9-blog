package com.siat.secretboard.common.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
    @Value("#{'${secretboard.config.cors.allowOrigns}'.trim().split(',')}")
    private List<String> allowedOrigins;
    @Bean
    public CorsConfigurationSource configurationSource(){
        CorsConfiguration cors=new CorsConfiguration();
        cors.setAllowedOrigins(allowedOrigins);
        cors.setAllowedMethods(List.of("*"));
        cors.setAllowedHeaders(List.of("*"));
        cors.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); 
        source.registerCorsConfiguration("/**", cors);
        return source;

    }
}
