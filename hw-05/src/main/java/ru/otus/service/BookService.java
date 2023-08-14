package ru.otus.service;

import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    List<Book> findByName(String bookName);

    List<Book> findByAuthor(Author author);

    List<Book> findByGenre(Genre genre);

    Book insert(String bookName, Long authorId, Long genreId);

    boolean deleteBook(Long bookId);
}
