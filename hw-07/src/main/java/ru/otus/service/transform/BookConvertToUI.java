package ru.otus.service.transform;

import java.util.List;

public interface BookConvertToUI {

    String findAll();

    String findByName(String bookName);

    String findByAuthorId(long authorId);

    String findByGenreId(long genreId);

    String insert(String bookName, List<Long> authors, List<Long> genres);

    String update(long bookId, String bookName);

    String delete(long bookId);
}
