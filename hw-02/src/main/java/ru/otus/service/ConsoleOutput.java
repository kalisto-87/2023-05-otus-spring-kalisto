package ru.otus.service;

import ru.otus.domain.Question;
import ru.otus.domain.Test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleOutput implements OutputService {
    @Override
    public void showTest(Test test) {

        ConsoleInput input = new ConsoleInput();
            
        for (Question question : test.getQuestionList()) {

            boolean isCorreсtInputValue = false;
            while (!isCorreсtInputValue) {
                try {
                    System.out.println(question);
                    Scanner scanner = new Scanner(System.in);
                    Integer answer = scanner.nextInt();
                    if (answer < 1 || answer > question.getAnswerOptions().size()) {
                        throw new RuntimeException("Answer should be between 1 and ".concat(
                                Integer.toString(question.getAnswerOptions().size())
                        ));
                    }
                    isCorreсtInputValue = true;
                } catch (InputMismatchException e) {
                    System.out.println("You must input only digits");
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }
    }
}
