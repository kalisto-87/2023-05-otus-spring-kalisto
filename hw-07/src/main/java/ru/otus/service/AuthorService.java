package ru.otus.service;

import ru.otus.domain.Author;

public interface AuthorService {
    String findAll();

    String findByName(String authorName);

    Author insert(String authorName);

    Author update(long authorId, String authorName);

    void delete(long authorId);
}
