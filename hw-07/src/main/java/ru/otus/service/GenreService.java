package ru.otus.service;

public interface GenreService {
    String findAll();

    String findByName(String genreName);

    String insert(String genreName);

    String update(long genreId, String genreName);

    String delete(long genreId);
}
