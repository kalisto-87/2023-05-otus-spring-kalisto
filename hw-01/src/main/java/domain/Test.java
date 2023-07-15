package domain;

import lombok.Data;

import java.util.List;

@Data
public class Test {
    private String testName;
    private List<Question> questionList;
}
