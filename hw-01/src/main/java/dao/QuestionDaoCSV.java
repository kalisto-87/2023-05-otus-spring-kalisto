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

public class QuestionDaoCSV implements QuestionDao {
    private final String path;

    public QuestionDaoCSV(String path) {
        this.path = path;
    }

    @Override
    public List<Question> getQuestions() {

        InputStream inputStream = this.getClass().getResourceAsStream(this.path);
        if (inputStream == null) {
            throw new RuntimeException(String.format("File %s has not been found", this.path));
        }
        CSVParser parser;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            parser = new CSVParser(inputStreamReader, CSVFormat.Builder.create().setDelimiter(';').build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Parsing exception");
        }

        return parseRecords(parser.getRecords());
    }

    private List<Question> parseRecords(List<CSVRecord> records) {

        List<Question> questionList = new ArrayList<>();

        for (CSVRecord r : records) {

            List<AnswerOption> answerOptions = new ArrayList<>();
            for (int i = 1; i < r.size(); i++) {
                AnswerOption answerOption = new AnswerOption(r.get(i).substring(0, r.get(i).indexOf("<")),
                        (r.get(i).contains("<true>")));
                answerOptions.add(answerOption);
            }
            Question question = new Question(r.get(0), answerOptions);
            questionList.add(question);
        }
        return questionList;
    }

}
