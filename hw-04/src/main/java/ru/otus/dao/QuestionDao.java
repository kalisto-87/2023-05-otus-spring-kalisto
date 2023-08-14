package ru.otus.dao;

import ru.otus.domain.Question;

import java.util.List;

public interface QuestionDao {
    public List<Question> getQuestions();
}
