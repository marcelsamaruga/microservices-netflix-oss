package com.appcom.yourturn.productservice.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.appcom.yourturn.productservice.controller"))
          .paths(PathSelectors.any())
          .build()
          .apiInfo(apiInfo());
    }
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Your Turn - Coffee", 
	      "Application to sort the members to prepare our tasty coffee morning", 
	      "Marcel",
	      "Terms of service", 
	      new Contact("Marcel Costa", "NA", "marcelsamaruga@gmail.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}
	
}
