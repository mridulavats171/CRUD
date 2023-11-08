//package com.task.employee.Config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.service.AuthorizationScope;
//import springfox.documentation.service.SecurityReference;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//    @Configuration
//    @EnableWebMvc
//    public class SwaggerConfiguration {
//
//        @Bean
//        public Docket api() {
//            return new Docket(DocumentationType.SWAGGER_2)
//                    .select()
//                    .apis(RequestHandlerSelectors.basePackage("com.task.employee.Controller"))
//                    .paths(PathSelectors.any())
//                    .build()
//                    .securitySchemes(Collections.singletonList(apiKey()))
//                    .securityContexts(securityContext());
//        }
//
//        private ApiKey apiKey() {
//            return new ApiKey("apiKey", "Authorization", "header");
//        }
//
//        private List<SecurityContext> securityContext() {
//            return List.of(SecurityContext.builder().securityReferences(defaultAuth())
//                    .forPaths(PathSelectors.any()).build());
//        }
//
//        private List<SecurityReference> defaultAuth() {
//            AuthorizationScope authorizationScope = new AuthorizationScope(
//                    "global", "accessEverything");
//            AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
//            authorizationScopes[0] = authorizationScope;
//            return Arrays.asList(new SecurityReference("apiKey",
//                    authorizationScopes));
//        }
//    }
//
//
////