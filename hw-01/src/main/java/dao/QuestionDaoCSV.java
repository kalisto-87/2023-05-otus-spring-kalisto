package dao;

import domain.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import service.CSVConverterToQuestion;

public class QuestionDaoCSV implements QuestionDao {
    private final String path;

    private final char delimiter;

    public QuestionDaoCSV(String path, char delimiter) {
        this.path = path;
        this.delimiter = delimiter;
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
            parser = new CSVParser(inputStreamReader, CSVFormat.Builder.create().setDelimiter(this.delimiter).build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Parsing exception");
        }
        return CSVConverterToQuestion.parseRecords(parser.getRecords());
    }
}
