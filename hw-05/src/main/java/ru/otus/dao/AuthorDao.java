package ru.otus.dao;

import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    List<Author> getAll();

    List<Author> findByName(String authorName);

    Optional<Author> findById(Long authorId);

    Author insertAuthor(String authorName);

    boolean updateAuthor(Long authorId, String authorName);

    boolean deleteAuthor(Long authorId);

}
