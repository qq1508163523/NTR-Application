package com.ntr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private static final String[] ORIGINS = {"HEAD","GET","POST","PUT","DELETE"};
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**").allowedOrigins("http://localhost:8080").
                allowCredentials(true).allowedMethods(ORIGINS).maxAge(10800);
    }
}
