package ru.otus.service;

import lombok.AllArgsConstructor;
import ru.otus.domain.Question;
import ru.otus.domain.Test;

import java.util.InputMismatchException;

@AllArgsConstructor
public class ConsoleOutput implements OutputService {

    private InputService inputService;

    @Override
    public void showTest(Test test) {

        for (Question question : test.getQuestionList()) {
            boolean isCorrectInputValue = false;
            while (!isCorrectInputValue) {
                try {
                    System.out.println(question);
                    Integer answer = inputService.inputInt();
                    Integer size = question.getAnswerOptions().size();
                    if (answer < 1 || answer > size) {
                        String s = "Inputted number should have value between 1 and ";
                        s = s.concat(Integer.toString(size));
                        throw new RuntimeException(s);
                    }
                    isCorrectInputValue = true;
                } catch (InputMismatchException e) {
                    System.out.println("You must inputService only integer values");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
