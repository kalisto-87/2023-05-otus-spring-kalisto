package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;
import ru.otus.domain.Test;
import lombok.Getter;
import ru.otus.domain.TestResult;
import ru.otus.domain.User;

import java.util.Map;

@Getter
@RequiredArgsConstructor
@Service
@PropertySource("classpath:application.properties")
public class TestServiceImpl implements TestService {

    @Value("${test.name}")
    private String testName;

    private final QuestionDao dao;

    private final OutputService outputService;

    private final UserInitService userInitService;

    @Override
    public void startTest() {
        User currentUser = userInitService.init();
        Test test = new Test(this.testName, dao.getQuestions());
        Map<Question, Integer> results = outputService.showTest(test);
        TestResult result = new TestResult(test, currentUser, results);
        TestResultService resultService = new TestResultService(result);
        outputService.showResults(resultService);
    }
}
