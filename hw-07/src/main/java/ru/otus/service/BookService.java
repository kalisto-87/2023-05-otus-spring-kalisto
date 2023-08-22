package ru.otus.service;

import ru.otus.domain.Book;

import java.util.List;

public interface BookService {

    String findAll();

    String findByName(String bookName);

    String findByAuthorId(long authorId);

    String findByGenreId(long genreId);

    Book insert(String bookName, List<Long> authorsId, List<Long> genresId);

    Book update(long bookId, String bookName);

    void delete(long authorId);
}
