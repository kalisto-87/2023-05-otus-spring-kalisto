package ru.otus.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;
import ru.otus.config.TestProps;
import ru.otus.domain.AnswerOption;
import ru.otus.domain.Question;
import ru.otus.domain.TestResult;

@Getter
@Service
@AllArgsConstructor
public class TestResultServiceImpl implements TestResultService {

    private TestProps testProps;

    @Override
    public Integer countCorrectAnswers(TestResult testResult) {

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

    @Override
    public boolean isTestPassed(TestResult testResult) {
        return (countCorrectAnswers(testResult) >= testProps.getCntAnswersForSuccess());
    }
}
