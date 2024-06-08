package com.example.iss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ImportResource("classpath:App.xml")
public class IssApplication {

    public static void main(String[] args) {
        SpringApplication.run(IssApplication.class, args);
    }

}
