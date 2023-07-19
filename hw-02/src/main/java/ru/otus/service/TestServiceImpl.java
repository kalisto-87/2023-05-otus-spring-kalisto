package ru.otus.service;

import ru.otus.dao.QuestionDao;
import ru.otus.domain.Test;
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
        Test test = new Test(this.testName, dao.getQuestions());
        outputService.showTest(test);
    }
}
