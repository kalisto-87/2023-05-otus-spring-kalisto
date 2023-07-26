package ru.otus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.config.AppProps;
import ru.otus.config.TestProps;
import ru.otus.dao.QuestionDaoCSV;
import ru.otus.domain.Question;
import ru.otus.domain.User;
import ru.otus.service.*;

import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.*;

public class TestServiceImplTest {

    private QuestionDaoCSV dao;

    private CSVConverterToQuestionImpl csvConverterToQuestion;

    private List<Question> questionList;

    private TestProps testProps;

    private AppProps appProps;

    private ConsoleInput consoleInput;

    private ConsoleOutput consoleOutput;

    private TestServiceImpl testService;

    private UserInitServiceImpl userInitService;

    private TestResultServiceImpl testResultService;

    private ReloadableResourceBundleMessageSource messageSource;

    @BeforeEach
    private void init() {
        csvConverterToQuestion = new CSVConverterToQuestionImpl();

        testProps = mock(TestProps.class);

        dao = new QuestionDaoCSV(testProps, csvConverterToQuestion);

        consoleInput = mock(ConsoleInput.class);

        appProps = mock(AppProps.class);

        testResultService = new TestResultServiceImpl(testProps);

        messageSource = new ReloadableResourceBundleMessageSource();

        consoleOutput = new ConsoleOutput(consoleInput, appProps, messageSource);

        userInitService = new UserInitServiceImpl(consoleInput, consoleOutput);
    }

    private void prepareValues(String locale) {
        when(testProps.getTestName()).thenReturn("SimpleTest");
        when(testProps.getFilePath()).thenReturn(String.format("/questions_%s.csv", locale));
        when(testProps.getDelimiter()).thenReturn(';');

        questionList = dao.getQuestions();

        ru.otus.domain.Test test = new ru.otus.domain.Test(testProps.getTestName(), dao.getQuestions());

        when(consoleInput.inputString()).thenReturn("Noname");
        when(consoleInput.inputInt()).thenReturn(2);

        when(appProps.getLocale()).thenReturn(new Locale(locale));

        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setFallbackToSystemLocale(true);
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setBasenames("/i18n/appmessages");
    }

    @Test
    public void startTestingEn() {
        prepareValues("en");
        testService = new TestServiceImpl(testProps, dao, consoleOutput, userInitService, testResultService);
        testService.startTest();
    }

    @Test
    public void startTestingDe() {
        prepareValues("de_De");
        testService = new TestServiceImpl(testProps, dao, consoleOutput, userInitService, testResultService);
        testService.startTest();
    }
}
