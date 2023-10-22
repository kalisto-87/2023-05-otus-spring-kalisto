package ru.otus.service.transform;

import java.util.List;

public interface BookConvertToUI {

    String findAll();

    String findByName(String bookName);

    String findByAuthorId(String authorId);

    String findByGenreId(String genreId);

    String insert(String bookName, List<String> authors, List<String> genres);

    String update(String bookId, String bookName);

    String delete(String bookId);
}
