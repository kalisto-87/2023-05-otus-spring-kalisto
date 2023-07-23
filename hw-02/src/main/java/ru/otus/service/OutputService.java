package ru.otus.service;

import ru.otus.domain.Question;
import ru.otus.domain.Test;
import ru.otus.domain.TestResult;

import java.util.Map;

public interface OutputService {
    public Map<Question, Integer> showTest(Test test);

    public void showResults(TestResult result, TestResultService resultService);
}
