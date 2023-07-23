package ru.otus.service;

import ru.otus.domain.TestResult;

public interface TestResultService {
    public Integer countCorrectAnswers(TestResult testResult);

    public boolean isTestPassed(TestResult testResult);
}
