package com.graduate.touslestemp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
//@EnableWebMvc
public class SwaggerConfig {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private ApiKey apiKey() {
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.graduate.touslestemp.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
//                .securityContexts(Arrays.asList(securityContext()))
//                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Touslestemp API",
                "Backend.",
                "API TOS",
                "Terms of service",
                new Contact("tamnlt2", "www.example.com", "imstudent2210@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }


//    @Bean
//    public WebMvcConfigurer webMvcConfigurer()
//    {
//        return new WebMvcConfigurer()
//        {
//            @Override
//            public void addResourceHandlers( ResourceHandlerRegistry registry )
//            {
//                registry.addResourceHandler( "swagger-ui.html" ).addResourceLocations( "classpath:/META-INF/resources/" );
//                registry.addResourceHandler( "/webjars/**" ).addResourceLocations( "classpath:/META-INF/resources/webjars/" );
//            }
//        };
//    }


}
