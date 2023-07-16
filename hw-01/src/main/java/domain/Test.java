package domain;

import lombok.Data;

import java.util.List;

@Data
public class Test {

    private String testName;

    private List<Question> questionList;

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
