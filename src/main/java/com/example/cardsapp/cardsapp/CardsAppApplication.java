package com.example.cardsapp.cardsapp;

import com.example.cardsapp.cardsapp.security.AppProperties;
import com.example.cardsapp.cardsapp.utils.utilities.SpringApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CardsAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(CardsAppApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringApplicationContext SpringApplicationContext() {
        return new SpringApplicationContext();
    }

    @Bean
    public AppProperties AppProperties() {
        return new AppProperties();
    }

}
