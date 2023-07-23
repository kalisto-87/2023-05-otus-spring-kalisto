package ru.otus.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.otus.domain.AnswerOption;
import ru.otus.domain.Question;
import ru.otus.domain.TestResult;

@Getter
@Service
@PropertySource("classpath:application.properties")
public class TestResultServiceImpl implements TestResultService {

    @Value("${test.cntforsuccess:3}")
    private Integer cntAnswersForPass;

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
        return (countCorrectAnswers(testResult) >= cntAnswersForPass);
    }
}
