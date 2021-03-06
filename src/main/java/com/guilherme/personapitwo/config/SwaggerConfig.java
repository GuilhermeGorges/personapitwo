package com.guilherme.personapitwo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket personApiTwo() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.guilherme.personapitwo"))
                .paths(PathSelectors.regex("/api/v1/people.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Person REST API",
                "API de controle de pessoas.",
                "API TOS",
                "Terms of service",
                new Contact("Guilherme Gabriel", "https://github.com/GuilhermeGorges",
                        "guilherme@gabriel.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", Collections.emptyList()
        );
        return apiInfo;
    }

}
