import dao.QuestionDaoCSV;
import domain.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
    
        QuestionDaoCSV d = new QuestionDaoCSV("/questions.csv");

        Test test = new Test();
        test.setQuestionList(d.getQuestions());


    }
}
