package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    private final TestService testService;

    @Override
    public void run(String... args) {
        testService.startTest();
    }
}
