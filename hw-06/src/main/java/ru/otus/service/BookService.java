package ru.otus.service;

import ru.otus.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    List<Book> findByName(String bookName);

    Book findById(long bookId);

    Book insert(Book book);

    Book update(long bookId, String bookName);

    void delete(long bookId);
}
