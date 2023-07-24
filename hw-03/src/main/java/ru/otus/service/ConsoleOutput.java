package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.Question;
import ru.otus.domain.Test;
import ru.otus.domain.TestResult;
import ru.otus.exception.AnswerOutOfBoundException;

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
            map.put(question, getAnswerFromConsole(question));
        }
        return map;
    }

    @Override
    public void showResults(TestResult result, TestResultService resultService) {
        System.out.println("Dear user(abuser), here you can the information about your test:");
        System.out.println(String.format("Surname: %s", result.getUser().getSurname()));
        System.out.println(String.format("Name: %s", result.getUser().getName()));
        System.out.println(String.format("Name of test: %s", result.getTest().getTestName()));
        System.out.println(String.format("Number of correct answers: %s",
                resultService.countCorrectAnswers(result).toString()));
        System.out.println(String.format("Test has been %s", resultService.isTestPassed(result) ? "passed" : "failed"));
    }

    private Integer getAnswerFromConsole(Question question) {
        while (true) {
            try {
                System.out.println(question);
                Integer answer = inputService.inputInt();
                Integer size = question.getAnswerOptions().size();
                checkAnswerNumber(answer, size);
                return answer;
            } catch (InputMismatchException e) {
                System.out.println("You must input only integer values");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void checkAnswerNumber(Integer answerNumber, Integer size) {
        if (answerNumber < 1 || answerNumber > size) {
            throw new AnswerOutOfBoundException(
                    "Inputted number should have value between 1 and ".concat(size.toString()));
        }
    }
}
