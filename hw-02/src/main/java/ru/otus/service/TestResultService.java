package ru.otus.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.otus.domain.AnswerOption;
import ru.otus.domain.Question;
import ru.otus.domain.TestResult;

@AllArgsConstructor
@Getter
public class TestResultService {

    private TestResult testResult;

    public Integer countCorrectAnswers() {

        Integer cntCorrectAnswers = 0;
        for (Question question : testResult.getTest().getQuestionList()) {
            Integer numberOfCorrectOption = 0;
            for (AnswerOption option : question.getAnswerOptions()) {
                numberOfCorrectOption += 1;
                if (option.isCorrect()) {
                    break;
                }
            }
            Integer userAnswer = testResult.getUserAnswers().get(question);
            if (userAnswer == numberOfCorrectOption) {
                cntCorrectAnswers += 1;
            }
        }
        return cntCorrectAnswers;
    }
}
