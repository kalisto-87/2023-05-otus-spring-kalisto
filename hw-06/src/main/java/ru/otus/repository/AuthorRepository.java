package ru.otus.repository;

import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> findAll();

    Optional<Author> findById(long authorId);

    List<Author> findByIds(List<Long> ids);

    List<Author> findByName(String authorName);

    Author insert(Author author);

    Author update(Author author);

    void delete(Author author);

    List<Author> findByBook(long bookId);
}
