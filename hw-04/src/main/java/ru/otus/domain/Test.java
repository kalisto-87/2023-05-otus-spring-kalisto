package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Test {

    private final String testName;

    private final List<Question> questionList;

}
