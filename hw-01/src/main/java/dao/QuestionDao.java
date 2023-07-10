package dao;

import domain.AnswerOption;
import domain.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class QuestionDao {
    private final String path;

    public QuestionDao(String path)
    {
        this.path = path;
    }

    public List<Question> getQuestions(){

        List<Question> questionList = new ArrayList<Question>();
        InputStream inputStream =
                this.getClass().getResourceAsStream(this.path);
        try {
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream);
            CSVParser parser = new CSVParser(inputStreamReader, CSVFormat.Builder.create().setDelimiter(';').build());

            for(CSVRecord r : parser.getRecords()) {

                Question question = new Question();
                question.setText(r.get(0));
                List<AnswerOption> answerOptions = new ArrayList<AnswerOption>();
                for(int i=1; i< r.size(); i++) {
                    AnswerOption answerOption = new AnswerOption();
                    answerOption.setAnswerText(
                            r.get(i).substring(0, r.get(i).indexOf("<")));
                    if ( r.get(i).contains("<true>") ) {
                        answerOption.setCorrect(true);
                    }
                    answerOptions.add(answerOption);
                }
                question.setAnswerOptions(answerOptions);
                questionList.add(question);
            }

            for(Question question : questionList)
            {
                System.out.println(question.toString());
            }
        }
        catch (Exception e)
        {

        }
        return questionList;
    }

}
