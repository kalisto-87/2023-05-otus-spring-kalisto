package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.AppProps;
import ru.otus.config.TestProps;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;
import ru.otus.domain.Test;
import lombok.Getter;
import ru.otus.domain.TestResult;
import ru.otus.domain.User;
import ru.otus.exception.AnswerOutOfBoundException;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

@Getter
@RequiredArgsConstructor
@Service
public class TestServiceImpl implements TestService {

    private final TestProps testProps;

    private final QuestionDao dao;

    private final OutputService outputService;

    private final UserInitService userInitService;

    private final TestResultService resultService;

    private final InputService inputService;

    private final AppProps appProps;

    private final MessageSource messageSource;

    @Override
    public void startTest(User user) {
        //User currentUser = userInitService.init();
        Test test = new Test(testProps.getTestName(), dao.getQuestions());
        Map<Question, Integer> results = showTest(test);
        TestResult result = new TestResult(test, user, results);
        outputService.showResults(result, resultService);
    }

    private Map<Question, Integer> showTest(Test test) {
        Map<Question, Integer> map = new HashMap<>();
        for (Question question : test.getQuestionList()) {
            map.put(question, getAnswerFromConsole(question));
        }
        return map;
    }

    private Integer getAnswerFromConsole(Question question) {
        while (true) {
            try {
                System.out.println(question);
                outputService.showMessages("question.enter", null);
                Integer answer = inputService.inputInt();
                Integer size = question.getAnswerOptions().size();
                checkAnswerNumber(answer, size);
                return answer;
            } catch (InputMismatchException e) {
                outputService.showMessages("warning.onlydigits", null);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void checkAnswerNumber(Integer answerNumber, Integer size) {
        if (answerNumber < 1 || answerNumber > size) {
            String[] args = new String[]{size.toString()};
            throw new AnswerOutOfBoundException(
                    messageSource.getMessage("warning.outofrange", args, appProps.getLocale())
            );
        }
    }
}