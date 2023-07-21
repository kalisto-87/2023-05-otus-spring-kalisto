package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.service.ConsoleInput;
import ru.otus.service.InputService;

@Configuration
public class InputServiceConfig {
    @Bean
    public InputService inputService() {
        return new ConsoleInput();
    }
}
