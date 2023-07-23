package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.Question;
import ru.otus.domain.Test;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

@Service
public class ConsoleOutput implements OutputService {

    private InputService inputService;

    public ConsoleOutput(InputService inputService) {
        this.inputService = inputService;
    }

    @Override
    public Map<Question, Integer> showTest(Test test) {

        Map<Question, Integer> map = new HashMap<>();
        for (Question question : test.getQuestionList()) {
            boolean isCorrectInputValue = false;
            while (!isCorrectInputValue) {
                try {
                    System.out.println(question);
                    Integer answer = inputService.inputInt();
                    Integer size = question.getAnswerOptions().size();
                    if (answer < 1 || answer > size) {
                        String s = "Inputted number should have value between 1 and ".concat(size.toString());
                        throw new RuntimeException(s);
                    }
                    isCorrectInputValue = true;
                    map.put(question, answer);
                } catch (InputMismatchException e) {
                    System.out.println("You must input only integer values");
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        return map;
    }

    @Override
    public void showResults(TestResultService result) {
        System.out.println("Dear user(abuser), here you can the information about your test:");
        System.out.println(String.format("Surname: %s", result.getTestResult().getUser().getSurname()));
        System.out.println(String.format("Name: %s", result.getTestResult().getUser().getName()));
        System.out.println(String.format("Name of test: %s", result.getTestResult().getTest().getTestName()));
        System.out.println(String.format("Number of correct answers: %s", result.countCorrectAnswers().toString()));
    }
}
