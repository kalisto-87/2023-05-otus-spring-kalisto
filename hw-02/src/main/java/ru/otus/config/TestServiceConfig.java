package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.dao.QuestionDao;
import ru.otus.service.TestService;
import ru.otus.service.TestServiceImpl;
import ru.otus.service.OutputService;
import ru.otus.service.UserInitService;

@PropertySource("classpath:application.properties")
@Configuration
public class TestServiceConfig {

    @Value("${test.name}")
    private String testName;

    @Bean
    public TestService testService(QuestionDao questionDao, OutputService outputService,
                                   UserInitService userInitService) {
        return new TestServiceImpl(testName, questionDao, outputService, userInitService);
    }

}
