package ru.otus.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.AppProps;
import ru.otus.domain.Question;
import ru.otus.domain.Test;
import ru.otus.domain.TestResult;
import ru.otus.exception.AnswerOutOfBoundException;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

@Service
public class ConsoleOutput implements OutputService {

    private final InputService inputService;

    private final AppProps appProps;

    private final MessageSource messageSource;

    public ConsoleOutput(InputService inputService, AppProps appProps, MessageSource messageSource) {
        this.inputService = inputService;
        this.appProps = appProps;
        this.messageSource = messageSource;
    }

    @Override
    public Map<Question, Integer> showTest(Test test) {

        Map<Question, Integer> map = new HashMap<>();
        for (Question question : test.getQuestionList()) {
            map.put(question, getAnswerFromConsole(question));
        }
        return map;
    }

    @Override
    public void showResults(TestResult result, TestResultService resultService) {
        showMessages("result.info", null);
        showMessages("result.surname", new String[]{result.getUser().getSurname()});
        showMessages("result.name", new String[]{result.getUser().getName()});
        showMessages("result.testname", new String[]{result.getTest().getTestName()});
        showMessages("result.correctanswers", new String[]{resultService.countCorrectAnswers(result).toString()});
        if (resultService.isTestPassed(result)) {
            showMessages("result.passed", null);
        } else {
            showMessages("result.failed", null);
        }
    }

    @Override
    public void showMessages(String message, String[] args) {
        System.out.println(messageSource.getMessage(message, args, appProps.getLocale()));
    }

    private Integer getAnswerFromConsole(Question question) {
        while (true) {
            try {
                System.out.println(question);
                Integer answer = inputService.inputInt();
                Integer size = question.getAnswerOptions().size();
                checkAnswerNumber(answer, size);
                return answer;
            } catch (InputMismatchException e) {
                showMessages("warning.onlydigits", null);
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
