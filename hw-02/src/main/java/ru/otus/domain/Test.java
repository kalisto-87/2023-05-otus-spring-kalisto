package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Test {

    private final String testName;

    private final List<Question> questionList;

    @Override
    public String toString() {
        String result = "Name of topic: ".concat(testName);
        for (Question question : questionList) {
            result = result.concat("\n");
            result = result.concat("Question text: ");
            result = result.concat(question.toString());
        }
        return result;
    }
}
