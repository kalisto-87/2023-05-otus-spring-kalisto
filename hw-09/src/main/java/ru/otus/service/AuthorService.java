package ru.otus.service;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    List<Author> findByName(String authorName);

    List<Author> findByIds(List<Long> authorsId);

    Author findById(long authorId);

    Author insert(Author author);

    Author update(long authorId, String authorName);

    void delete(long authorId);
}
