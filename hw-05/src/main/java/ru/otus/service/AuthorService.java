package ru.otus.service;

import ru.otus.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findByName(String authorName);

    List<Author> getAll();

    Author findById(Long authorId);

    Author insert(String authorName);

    boolean update(Long id, String authorName);

    boolean delete(Long id);

}
