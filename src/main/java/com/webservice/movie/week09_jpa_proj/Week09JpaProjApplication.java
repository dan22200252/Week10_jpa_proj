package com.webservice.movie.week09_jpa_proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Week09JpaProjApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week09JpaProjApplication.class, args);
    }

}
