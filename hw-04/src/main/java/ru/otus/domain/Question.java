package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Question {

    private final String text;

    private final List<AnswerOption> answerOptions;

}
