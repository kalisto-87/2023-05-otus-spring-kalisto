package ru.otus.dao;

import ru.otus.domain.Book;

import java.util.List;

public interface BookDao {

    List<Book> getAll();

    List<Book> findByName(String bookName);

    Book findById(Long bookId);

    Book insertBook(String bookName, Long authorId, Long genreId);

    boolean deleteBook(Long bookId);
}
