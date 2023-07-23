package ru.otus.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import ru.otus.service.CSVConverterToQuestion;

@Repository
@PropertySource("classpath:application.properties")
public class QuestionDaoCSV implements QuestionDao {
    @Value("${test.filepath}")
    private String path;

    @Value("${test.delimiter}")
    private String delimiter;

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
