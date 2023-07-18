package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Question {

    private String text = "";

    private List<AnswerOption> answerOptions;

    @Override
    public String toString() {
        String res = "";
        for (AnswerOption answerOption : answerOptions) {
            res = res.concat("\n");
            res = res.concat(answerOption.getAnswerText());
        }
        return this.text.concat(res);
    }
}
