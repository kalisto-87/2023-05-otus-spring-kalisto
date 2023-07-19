package ru.otus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.dao.QuestionDao;
import ru.otus.dao.QuestionDaoCSV;

@Configuration
public class QuestionDaoConfig {
    @Bean
    public QuestionDao questionDao() {
        return new QuestionDaoCSV("/questions.csv", ';');
    }
}
