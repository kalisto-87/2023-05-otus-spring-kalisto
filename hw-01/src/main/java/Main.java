import dao.QuestionDaoCSV;
import domain.Test;
import domain.User;

public class Main {
    public static void main(String[] args) {

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

        User user = new User();
        user.setName("");
        user.setSurname("");

        //InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("questions.csv");
        QuestionDaoCSV d = new QuestionDaoCSV("/questions.csv");

        Test test = new Test();
        test.setQuestionList(d.getQuestions());
        //user.setTest(test);


    }
}
