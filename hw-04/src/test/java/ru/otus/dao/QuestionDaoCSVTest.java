package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.domain.AnswerOption;
import ru.otus.domain.Question;
import ru.otus.service.CSVConverterToQuestionImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Получение")
@SpringBootTest(properties = "spring.=")
public class QuestionDaoCSVTest {

    @MockBean
    private QuestionDaoCSV questionDaoCSV;

    @MockBean
    private CSVConverterToQuestionImpl csvConverterToQuestion;

    private List<Question> questionList;

    @BeforeEach
    private void prepare() {
        csvConverterToQuestion = new CSVConverterToQuestionImpl();
    }

    @Test
    @DisplayName("списка вопросов")
    public void checkGettingQuestions() {
        List<Question> questionListGot = new ArrayList<>(
                List.of(new Question("Question1",
                                List.of(new AnswerOption("Answer1", true),
                                        new AnswerOption("Answer2", false),
                                        new AnswerOption("Answer3", false),
                                        new AnswerOption("Answer4", false))
                        ),
                        new Question("Question2",
                                List.of(new AnswerOption("Answer1", false),
                                        new AnswerOption("Answer2", false),
                                        new AnswerOption("Answer3", true),
                                        new AnswerOption("Answer4", false))
                        )));
        when(questionDaoCSV.getQuestions()).thenReturn(questionListGot);
        questionList = questionDaoCSV.getQuestions();
        assertEquals(2, questionList.size());
        assertEquals(questionListGot, questionList);
        verify(questionDaoCSV).getQuestions();
    }
}
