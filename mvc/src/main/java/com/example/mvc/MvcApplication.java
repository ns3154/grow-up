package com.example.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.handler.MappedInterceptor;

@SpringBootApplication
public class MvcApplication {

    public static void main (String[] args) {
        SpringApplication.run(MvcApplication.class, args);
    }

}
