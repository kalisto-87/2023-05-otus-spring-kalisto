package ru.otus.service;

import org.apache.commons.csv.CSVRecord;
import ru.otus.domain.Question;

import java.util.List;

public interface CSVConverterToQuestion {
    public List<Question> parseRecords(List<CSVRecord> records);
}
