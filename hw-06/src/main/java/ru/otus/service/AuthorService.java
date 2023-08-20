package ru.otus.service;

public interface AuthorService {
    String findAll();

    String findByName(String authorName);

    String insert(String authorName);

    String update(long authorId, String authorName);

    String delete(long authorId);
}
