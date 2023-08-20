package ru.otus.repository;

import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> findAll();

    Optional<Book> findById(long bookId);

    List<Book> findByName(String bookName);

    Book insert(Book book);

    boolean update(Book book);

    boolean delete(long bookId);
}
