package ru.otus.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.config.TestProps;
import ru.otus.domain.Question;
import ru.otus.service.CSVConverterToQuestionImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionDaoCSVTest {

    private QuestionDaoCSV questionDaoCSV;

    private CSVConverterToQuestionImpl csvConverterToQuestion;

    private List<Question> questionList;

    @BeforeEach
    private void prepare() {
        csvConverterToQuestion = new CSVConverterToQuestionImpl();
    }

    @Test
    public void checkGettingQuestionsEn() {
        TestProps testProps = getMockTestProps("/questions_en.csv");
        questionDaoCSV = new QuestionDaoCSV(testProps, csvConverterToQuestion);
        questionList = questionDaoCSV.getQuestions();
        assertEquals(5, questionList.size());
    }

    @Test
    public void checkGettingQuestionsDe() {
        TestProps testProps = getMockTestProps("/questions_de_DE.csv");
        questionDaoCSV = new QuestionDaoCSV(testProps, csvConverterToQuestion);
        questionList = questionDaoCSV.getQuestions();
        assertEquals(5, questionList.size());
    }

    private TestProps getMockTestProps(String fileName) {
        TestProps testProps = mock(TestProps.class);
        when(testProps.getTestName()).thenReturn("SimpleTest");
        when(testProps.getFilePath()).thenReturn(fileName);
        when(testProps.getDelimiter()).thenReturn(';');
        return testProps;
    }
}
