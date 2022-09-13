package com.bridgelabz.bookstore;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Slf4j
public class BookStoreApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BookStoreApplication.class, args);
        log.info("Book Store Application Started in {} Environment", context.getEnvironment().getProperty("environment"));
        log.info("Book Store DB User is {}", context.getEnvironment().getProperty("spring.datasource.username"));
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
