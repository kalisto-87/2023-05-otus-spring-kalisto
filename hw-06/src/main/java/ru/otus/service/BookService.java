package ru.otus.service;

import java.util.List;

public interface BookService {

    String findAll();

    String findByName(String bookName);

    String insert(String bookName, List<Long> authorsId, List<Long> genreIds);

    String update(long bookId, String bookName);

    String delete(long authorId);
}
