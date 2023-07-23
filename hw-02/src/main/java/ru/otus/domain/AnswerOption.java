package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AnswerOption {

    private final String answerText;

    private final boolean isCorrect;
}
