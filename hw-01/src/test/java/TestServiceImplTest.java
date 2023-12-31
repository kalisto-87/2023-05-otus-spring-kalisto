import dao.QuestionDao;
import dao.QuestionDaoCSV;
import org.junit.jupiter.api.*;
import service.ConsoleOutput;
import service.OutputService;
import service.TestServiceImpl;

public class TestServiceImplTest {

    private final String correctFileName = "/questions.csv";

    private final String inCorrectFileName = "/none.csv";

    private final char delimiter = ';';

    private TestServiceImpl testService;

    private QuestionDao questionDao;

    private OutputService outputService;

    @BeforeEach
    void testInit() {
        outputService = new ConsoleOutput();
    }

    @Test
    @DisplayName("Тест c указанным в настройках файлом запросов")
    void checkUsualTest() {
        questionDao = new QuestionDaoCSV(correctFileName, delimiter);
        testService = new TestServiceImpl("MyFirstTest", questionDao, outputService);
        testService.startTest();
        Assertions.assertEquals(5, testService.getDao().getQuestions().size());
    }

    @Test
    @DisplayName("Тест с несуществующим файлом")
     void checkNonExistingFile() {
        questionDao = new QuestionDaoCSV(inCorrectFileName, delimiter);
        testService = new TestServiceImpl("MyFirstTest", questionDao, outputService);
        RuntimeException re = Assertions.assertThrows(RuntimeException.class, () -> {
            testService.startTest();
        }, "RuntimeException was expected");
        Assertions.assertEquals(String.format("File %s has not been found", inCorrectFileName), re.getMessage());
    }


}
