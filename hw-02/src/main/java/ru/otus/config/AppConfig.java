package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.dao.QuestionDao;
import ru.otus.dao.QuestionDaoCSV;
import ru.otus.service.InputService;
import ru.otus.service.OutputService;
import ru.otus.service.TestService;
import ru.otus.service.UserInitService;
import ru.otus.service.TestServiceImpl;
import ru.otus.service.UserInitServiceImpl;
import ru.otus.service.ConsoleInput;
import ru.otus.service.ConsoleOutput;

@PropertySource("classpath:application.properties")
@Configuration
public class AppConfig {

    @Value("${test.filepath}")
    private String path;

    @Value("${test.delimiter}")
    private char delimiter;

    @Value("${test.name}")
    private String testName;

    @Bean
    public TestService testService(QuestionDao questionDao, OutputService outputService,
                                   UserInitService userInitService) {
        return new TestServiceImpl(testName, questionDao, outputService, userInitService);
    }

    @Bean
    public QuestionDao questionDao() {
        return new QuestionDaoCSV(path, delimiter);
    }

    @Bean
    public UserInitService userInitService(InputService inputService) {
        return new UserInitServiceImpl(inputService);
    }

    @Bean
    public InputService inputService() {
        return new ConsoleInput();
    }

    @Bean
    public OutputService outputService(InputService inputService) {
        return new ConsoleOutput(inputService);
    }

}
