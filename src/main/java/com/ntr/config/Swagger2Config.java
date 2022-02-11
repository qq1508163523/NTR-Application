package com.ntr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket docket(Environment environment){
        Profiles profiles = Profiles.of("dev","test"); // env
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(flag)
                .groupName("Vincent Li")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ntr.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Vincent Li","localhost:8080","miaoxinli234@gmail.com");
        return new ApiInfo("Brahman NTR Backend API Documentation",
                "API Documentation",
                "v1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}
