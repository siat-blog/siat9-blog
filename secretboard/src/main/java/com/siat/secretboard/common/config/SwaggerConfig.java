package com.siat.secretboard.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        SecurityScheme apiKey = new SecurityScheme()
        .type(SecurityScheme.Type.APIKEY)
        .in(SecurityScheme.In.HEADER)
        .name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement()
        .addList("Bearer Token");


      return new OpenAPI()
      .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
      .info(new Info().title("SIAT SecretBoard API").version("v1").description("SIAT 9기 SecretBoard 프로젝트 API"))
      .addSecurityItem(securityRequirement);
    }
}
