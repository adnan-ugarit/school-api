package com.api.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@EnableWebMvc
public class SwaggerConfig {
    
    @Bean
    public Docket api() {
        ApiInfoBuilder builder = new ApiInfoBuilder()
                .title("School API Service Documentation")
                .version("1.0.0");
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.api.school.controller"))
                .paths(PathSelectors.any()).build()
                .apiInfo(builder.build());
    }
    
}
