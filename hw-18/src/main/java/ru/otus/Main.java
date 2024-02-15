package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.sql.SQLException;

@EnableWebSecurity
@SpringBootApplication
public class Main {
    public static void main(String[] args) throws SQLException {

        SpringApplication.run(Main.class);
    }
}