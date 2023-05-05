package com.fqh.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        var ioc = SpringApplication.run(BackendApplication.class, args);
    }
}
