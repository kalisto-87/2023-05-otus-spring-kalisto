package ru.otus.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.config.AppProps;
import ru.otus.domain.Question;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import ru.otus.service.CSVConverterToQuestion;

@Component
@AllArgsConstructor
public class QuestionDaoCSV implements QuestionDao {

    private final AppProps appProps;

    @Override
    public List<Question> getQuestions() {

        InputStream inputStream = this.getClass().getResourceAsStream(appProps.getFilePath());
        if (inputStream == null) {
            throw new RuntimeException(String.format("File %s has not been found", appProps.getFilePath()));
        }
        CSVParser parser;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            parser = new CSVParser(inputStreamReader,
                    CSVFormat.Builder.create().setDelimiter(appProps.getDelimiter()).build());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Parsing exception");
        }
        return CSVConverterToQuestion.parseRecords(parser.getRecords());
    }
}
