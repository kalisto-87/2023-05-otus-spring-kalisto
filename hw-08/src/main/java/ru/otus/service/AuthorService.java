package ru.otus.service;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    List<Author> findByName(String authorName);

    List<Author> findByIds(List<String> authorsId);

    Author findById(String authorId);

    Author insert(Author author);

    Author update(String authorId, String authorName);

    void delete(String authorId);
}
