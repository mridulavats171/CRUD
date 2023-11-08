package com.task.employee.Config;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebMvc
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private ApiKey apiKeys(){
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    @Bean
    private List<SecurityContext> securityContexts(){
        return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences())
                .build());
    }

    private List<SecurityReference> securityReferences(){
//        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
//    return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scope}));
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContexts())
                .securitySchemes(Arrays.asList(apiKeys()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.task.employee.Controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getInfo() {

        return new ApiInfo("Employee application", "Get information about employees", "1.0","terms of Service",
                new Contact("mridula", "jxded", "mridula@gmail.com"),
                "API license url", "url",Collections.emptyList());
    }

}
