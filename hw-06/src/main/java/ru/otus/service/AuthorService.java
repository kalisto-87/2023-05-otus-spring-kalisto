package ru.otus.service;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    List<Author> findByName(String authorName);

    Author findById(long authorsId);

    List<Author> findByIds(List<Long> authorsId);

    Author insert(Author author);

    Author update(long authorId, String authorName);

    void delete(long authorId);
}
