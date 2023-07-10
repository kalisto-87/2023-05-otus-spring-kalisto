import dao.QuestionDao;
import domain.AnswerOption;
import domain.Question;
import domain.User;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("1");
        /*Question question = new Question();

        question.setText("Question?");
        List<AnswerOption> answers = new ArrayList<AnswerOption>();
        AnswerOption answerOption = new AnswerOption("Option1", false);
        answers.add(answerOption);
        answerOption = new AnswerOption("Option2", false);
        answers.add(answerOption);
        answerOption = new AnswerOption("Option3", false);
        answers.add(answerOption);
        question.setAnswerOptions(answers);

        System.out.println(question);
        */


        //InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("questions.csv");
        QuestionDao d = new QuestionDao("/questions.csv");
        d.getQuestions();
    }
}
