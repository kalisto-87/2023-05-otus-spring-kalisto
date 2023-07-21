package ru.otus.service;

import ru.otus.domain.Question;
import ru.otus.domain.Test;

import java.util.Map;

public interface OutputService {
    public Map<Question, Integer> showTest(Test test);

    public void showResults(TestResultService result);
}
