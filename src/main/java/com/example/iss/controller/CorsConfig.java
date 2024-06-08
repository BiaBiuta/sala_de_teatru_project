package com.example.iss.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:4200",
                        "http://localhost:4300",
                        "https://navigatorqualityangularclient.azurewebsites.net",
                        "https://navigatordevangularclient.azurewebsites.net",
                        "https://navigatorprodangularclient.azurewebsites.net",
                        "https://navigatorqualitylanguageangularclient.azurewebsites.net",
                        "https://webnavigator.guehring.de"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS", "PATCH")
                .allowCredentials(true)
                .allowedHeaders("*");
    }
}
