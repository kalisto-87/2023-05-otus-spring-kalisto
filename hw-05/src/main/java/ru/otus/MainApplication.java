package ru.otus;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(MainApplication.class);
        Console.main(args);
    }
}
