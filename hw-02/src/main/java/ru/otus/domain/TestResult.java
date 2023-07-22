package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class TestResult {
    private final Test test;

    private final User user;

    private final Map<Question, Integer> userAnswers;
}
