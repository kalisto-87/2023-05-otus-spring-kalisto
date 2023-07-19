package ru.otus.service;

import ru.otus.domain.Test;

public class ConsoleOutput implements OutputService {
    @Override
    public void showTest(Test test) {
        System.out.println(test);
    }
}
