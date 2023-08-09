package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.AppProps;
import ru.otus.config.TestProps;
import ru.otus.dao.QuestionDao;
import lombok.Getter;
import ru.otus.domain.AnswerOption;
import ru.otus.domain.Question;
import ru.otus.domain.Test;
import ru.otus.domain.TestResult;
import ru.otus.domain.User;
import ru.otus.exception.AnswerOutOfBoundException;
import ru.otus.exception.QuestionExecutingException;

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

    private TestResult result;

    @Override
    public void startTest(User user) {
        Test test = new Test(testProps.getTestName(), dao.getQuestions());
        Map<Question, Integer> results = showTest(test);
        result = new TestResult(test, user, results);
        outputService.showResults(result, resultService);
    }

    private Map<Question, Integer> showTest(Test test) {
        Map<Question, Integer> map = new HashMap<>();
        for (Question question : test.getQuestionList()) {
            map.put(question, getAnswerFromConsole(question));
        }
        return map;
    }

    private Integer getAnswerFromConsole(Question question) throws QuestionExecutingException {
        while (true) {
            try {
                outputService.showMessages("question." + question.getText(), null);
                for (AnswerOption answerOption : question.getAnswerOptions()) {
                    outputService.showMessages("question." +
                            question.getText() + "." +
                            answerOption.getAnswerText(), null);
                }
                outputService.showMessages("question.enter", null);
                Integer answer = inputService.inputInt();
                Integer size = question.getAnswerOptions().size();
                checkAnswerNumber(answer, size);
                return answer;
            } catch (InputMismatchException e) {
                outputService.showMessages("warning.onlydigits", null);
            } catch (AnswerOutOfBoundException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new QuestionExecutingException(e.getMessage());
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
