package ru.otus.repository;

import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> findAll();

    Optional<Author> findById(long authorId);

    List<Author> findByName(String authorName);

    Author insert(Author author);

    boolean update(Author author);

    boolean delete(long authorId);
}
