package ru.otus.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.otus.domain.AnswerOption;
import ru.otus.domain.Question;
import ru.otus.domain.Test;
import ru.otus.domain.User;

import java.util.Map;

@AllArgsConstructor
@Getter
public class TestResultService {

    private User user;

    private Test test;

    private Map<Question, Integer> answers;

    public Integer countCorrectAnswers() {

        Integer cntCorrectAnswers = 0;
        for (Question question : test.getQuestionList()) {
            Integer numberOfCorrectOption = 0;
            for (AnswerOption option : question.getAnswerOptions()) {
                numberOfCorrectOption += 1;
                if (option.isCorrect()) {
                    break;
                }
            }
            Integer userAnswer = answers.get(question);
            if (userAnswer == numberOfCorrectOption) {
                cntCorrectAnswers += 1;
            }
        }
        return cntCorrectAnswers;
    }
}
