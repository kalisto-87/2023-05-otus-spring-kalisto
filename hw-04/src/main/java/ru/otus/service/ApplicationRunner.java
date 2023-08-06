package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.otus.domain.User;

@Service
@RequiredArgsConstructor
public class ApplicationRunner implements CommandLineRunner {

    private final TestService testService;

    private final UserInitService userInitService;

    @Override
    public void run(String... args) {
        User currentUser = userInitService.init();
        testService.startTest(currentUser);
    }
}
