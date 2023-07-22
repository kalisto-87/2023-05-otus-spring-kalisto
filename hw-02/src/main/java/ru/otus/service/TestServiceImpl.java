package ru.otus.service;

import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;
import ru.otus.domain.Test;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.otus.domain.TestResult;
import ru.otus.domain.User;

import java.util.Map;

@Getter
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private final String testName;

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
