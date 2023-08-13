package ru.otus.service;

import ru.otus.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    List<Book> findByName(String bookName);

    Book insert(String bookName, Long authorId, Long genreId);

    boolean deleteBook(Long bookId);
}
