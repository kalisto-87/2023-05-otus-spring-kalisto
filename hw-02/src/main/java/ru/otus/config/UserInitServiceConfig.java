package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.service.InputService;
import ru.otus.service.UserInitService;
import ru.otus.service.UserInitServiceImpl;

@Configuration
public class UserInitServiceConfig {
    @Bean
    public UserInitService userInitService(InputService inputService) {
        return new UserInitServiceImpl(inputService);
    }
}
