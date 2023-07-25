package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.AnswerOption;
import ru.otus.domain.Question;
import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.ArrayList;

@Service
public class CSVConverterToQuestionImpl implements CSVConverterToQuestion {

    public List<Question> parseRecords(List<CSVRecord> records) {
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
