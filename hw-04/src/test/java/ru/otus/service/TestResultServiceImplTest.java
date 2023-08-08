package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import ru.otus.config.AppProps;
import ru.otus.config.TestProps;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.AnswerOption;
import ru.otus.domain.Question;
import ru.otus.domain.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Проверка результатов сервиса:")
@SpringBootTest(properties = "spring.=")
public class TestResultServiceImplTest {

    @MockBean
    private TestProps testProps;

    @MockBean
    private QuestionDao dao;

    @MockBean
    private OutputService outputService;

    @MockBean
    private UserInitService userInitService;

    @MockBean
    private InputService inputService;

    @MockBean
    private AppProps appProps;

    @MockBean
    private MessageSource messageSource;

    private List<Question> questionList;

    private User user;

    private TestServiceImpl testService;

    private TestResultService testResultService;

    @BeforeEach
    private void setUp() {

        questionList = new ArrayList<>(
                List.of(new Question("Question1",
                                List.of(new AnswerOption("Answer1", true),
                                        new AnswerOption("Answer2", false),
                                        new AnswerOption("Answer3", false),
                                        new AnswerOption("Answer4", false))
                        ),
                        new Question("Question2",
                                List.of(new AnswerOption("Answer1", true),
                                        new AnswerOption("Answer2", false),
                                        new AnswerOption("Answer3", false),
                                        new AnswerOption("Answer4", false))
                        ),
                        new Question("Question3",
                                List.of(new AnswerOption("Answer1", true),
                                        new AnswerOption("Answer2", false),
                                        new AnswerOption("Answer3", false),
                                        new AnswerOption("Answer4", false))
                        ),
                        new Question("Question4",
                                List.of(new AnswerOption("Answer1", true),
                                        new AnswerOption("Answer2", false),
                                        new AnswerOption("Answer3", false),
                                        new AnswerOption("Answer4", false))
                        ),
                        new Question("Question5",
                                List.of(new AnswerOption("Answer1", false),
                                        new AnswerOption("Answer2", false),
                                        new AnswerOption("Answer3", true),
                                        new AnswerOption("Answer4", false))
                        )));
        user = new User("Ivan", "Ivanov");
    }

    @DisplayName("Проверка подчсёта кол-ва верных ответов")
    @Test
    public void checkCountOfCorrectAnswers() {

        when(dao.getQuestions()).thenReturn(questionList);
        when(inputService.inputInt()).thenReturn(1);
        when(testProps.getCntAnswersForSuccess()).thenReturn(3);

        testResultService = new TestResultServiceImpl(testProps);
        testService = new TestServiceImpl(testProps, dao, outputService, userInitService, testResultService, inputService, appProps, messageSource);
        testService.startTest(user);

        assertEquals(4, testResultService.countCorrectAnswers(testService.getResult()));
        assertEquals(true, testResultService.isTestPassed(testService.getResult()));
    }

    @DisplayName("Проверка, что тест не пройден при малом кол-ве правильных ответов")
    @Test
    public void checkPassingTest() {

        when(dao.getQuestions()).thenReturn(questionList);
        when(inputService.inputInt()).thenReturn(2);
        when(testProps.getCntAnswersForSuccess()).thenReturn(3);

        testResultService = new TestResultServiceImpl(testProps);
        testService = new TestServiceImpl(testProps, dao, outputService, userInitService, testResultService, inputService, appProps, messageSource);
        testService.startTest(user);

        assertEquals(false, testResultService.isTestPassed(testService.getResult()));
    }
}
