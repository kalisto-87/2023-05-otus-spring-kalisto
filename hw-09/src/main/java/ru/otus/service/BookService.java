package ru.otus.service;

import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    List<Book> findByName(String bookName);

    Optional<Book> findById(long bookId);

    List<Book> findByAuthorId(Author author);

    List<Book> findByGenreId(Genre genre);

    Book insert(Book book);

    Book update(long bookId, String bookName);

    void delete(long bookId);
}
