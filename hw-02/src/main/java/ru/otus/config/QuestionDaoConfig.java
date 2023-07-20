package ru.otus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.dao.QuestionDao;
import ru.otus.dao.QuestionDaoCSV;

@PropertySource("classpath:application.properties")
@Configuration
public class QuestionDaoConfig {
    @Value("${test.filepath}")
    private String path;

    @Value("${test.delimiter}")
    private char delimiter;

    @Bean
    public QuestionDao questionDao() {
        return new QuestionDaoCSV(path, delimiter);
    }
}
