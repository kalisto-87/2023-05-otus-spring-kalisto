package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.dao.QuestionDao;
import ru.otus.service.OutputService;
import ru.otus.service.TestService;
import ru.otus.service.TestServiceImpl;

@Configuration
public class TestServiceConfig {

    @Bean
    public TestService testService(QuestionDao questionDao, OutputService outputService) {
        return new TestServiceImpl("MyFirstTest", questionDao, outputService);
    }

}
