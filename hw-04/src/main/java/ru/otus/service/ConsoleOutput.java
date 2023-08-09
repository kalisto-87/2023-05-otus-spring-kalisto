package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.config.AppProps;
import ru.otus.domain.TestResult;

@Service
public class ConsoleOutput implements OutputService {

    private final AppProps appProps;

    private final MessageSource messageSource;

    @Autowired
    public ConsoleOutput(AppProps appProps, MessageSource messageSource) {
        this.appProps = appProps;
        this.messageSource = messageSource;
    }

    @Override
    public void showMessages(String message, String[] args) {
        System.out.println(messageSource.getMessage(message, args, appProps.getLocale()));
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
}
