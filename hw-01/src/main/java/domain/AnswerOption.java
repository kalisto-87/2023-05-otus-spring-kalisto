package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AnswerOption {

    private String answerText;

    private boolean isCorrect;
}
