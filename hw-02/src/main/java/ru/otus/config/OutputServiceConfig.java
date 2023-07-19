package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.service.ConsoleOutput;
import ru.otus.service.OutputService;

@Configuration
public class OutputServiceConfig {
    @Bean
    public OutputService outputService() {
        return new ConsoleOutput();
    }
}
