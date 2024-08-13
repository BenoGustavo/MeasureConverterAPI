package com.api.measureconverter.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    private static final String[] END_POINTS = {
            "/api/convert/**",
    };

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch(END_POINTS)
                .group("public-api")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("All in One Converter API")
                        .version("1.0")
                        .description("An all-in-one API for converting units")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}