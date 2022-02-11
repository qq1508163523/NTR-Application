package com.ntr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableAsync
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrahmanApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BrahmanApiApplication.class, args);
    }
}
