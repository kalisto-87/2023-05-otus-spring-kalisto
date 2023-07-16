package service;

import domain.Test;

public class ConsoleOutput implements OutputService {
    @Override
    public void showTest(Test test) {
        System.out.println(test);
    }
}
