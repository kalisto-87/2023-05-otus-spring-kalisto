package ru.otus.dao;

import ru.otus.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAll();

    Book findByName(String name);

    void insertBook(String name);

    void updateBook(Long bookId, String name);

    void deleteBook(Long bookId);
}
