package com.example.registerstartupproject.security.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    String frontendLink;

    public CorsConfig(@Value("${frontEndLink}") String frontendLink) {
        this.frontendLink = frontendLink;
    }
    ;


    @Bean
    public WebMvcConfigurer getCorsConfiguration() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedHeaders("*")
                        .allowedOrigins(frontendLink)
                        .allowedMethods("*")
                        .allowCredentials(true).maxAge(3600);


            }
        };
    }
}
