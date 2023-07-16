package service;

import dao.QuestionDao;
import domain.Test;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private final String testName;

    private final QuestionDao dao;

    private final OutputService outputService;

    @Override
    public void startTest() {
        Test test = new Test();
        test.setTestName(this.testName);
        test.setQuestionList(dao.getQuestions());
        outputService.showTest(test);
    }
}
