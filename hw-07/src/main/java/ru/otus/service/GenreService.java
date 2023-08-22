package ru.otus.service;

import ru.otus.domain.Genre;

public interface GenreService {
    String findAll();

    String findByName(String genreName);

    Genre insert(String genreName);

    Genre update(long genreId, String genreName);

    void delete(long genreId);
}
