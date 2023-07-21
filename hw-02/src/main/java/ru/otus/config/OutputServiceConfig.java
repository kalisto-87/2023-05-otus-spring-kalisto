package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.service.ConsoleOutput;
import ru.otus.service.InputService;
import ru.otus.service.OutputService;

@Configuration
public class OutputServiceConfig {
    @Bean
    public OutputService outputService(InputService inputService) {
        return new ConsoleOutput(inputService);
    }
}
