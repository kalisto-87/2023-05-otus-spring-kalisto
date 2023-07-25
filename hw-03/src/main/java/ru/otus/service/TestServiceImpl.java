package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.config.TestProps;
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
public class TestServiceImpl implements TestService {

    private final TestProps testProps;

    private final QuestionDao dao;

    private final OutputService outputService;

    private final UserInitService userInitService;

    private final TestResultService resultService;

    @Override
    public void startTest() {
        User currentUser = userInitService.init();
        Test test = new Test(testProps.getTestName(), dao.getQuestions());
        Map<Question, Integer> results = outputService.showTest(test);
        TestResult result = new TestResult(test, currentUser, results);
        outputService.showResults(result, resultService);
    }
}
