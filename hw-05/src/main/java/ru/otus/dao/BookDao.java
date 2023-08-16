package ru.otus.dao;

import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

public interface BookDao {

    List<Book> getAll();

    List<Book> findByName(String bookName);

    List<Book> findByAuthor(Author author);

    List<Book> findByGenre(Genre genre);

    Book findById(Long bookId);

    Book insertBook(String bookName, Long authorId, Long genreId);

    boolean deleteBook(Long bookId);
}
