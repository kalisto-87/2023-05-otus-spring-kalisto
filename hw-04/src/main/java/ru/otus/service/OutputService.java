package ru.otus.service;

import ru.otus.domain.TestResult;

public interface OutputService {

    public void showMessages(String message, String[] args);

    public void showResults(TestResult result, TestResultService resultService);

}
