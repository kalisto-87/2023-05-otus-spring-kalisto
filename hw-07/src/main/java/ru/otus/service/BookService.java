package ru.otus.service;

import java.util.List;

public interface BookService {

    String findAll();

    String findByName(String bookName);

    String findByAuthorId(long authorId);

    String findByGenreId(long genreId);

    String insert(String bookName, List<Long> authorsId, List<Long> genresId);

    String update(long bookId, String bookName);

    String delete(long authorId);
}
