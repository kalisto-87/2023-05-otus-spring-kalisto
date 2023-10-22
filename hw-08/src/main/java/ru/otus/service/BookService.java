package ru.otus.service;

import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    List<Book> findByName(String bookName);

    Optional<Book> findById(String bookId);

    List<Book> findByAuthorId(String author);

    List<Book> findByGenreId(String genre);

    Book insert(Book book);

    Book update(String bookId, String bookName);

    void delete(String bookId);
}
