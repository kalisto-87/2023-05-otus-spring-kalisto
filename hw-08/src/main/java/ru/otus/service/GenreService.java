package ru.otus.service;

import ru.otus.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();

    List<Genre> findByName(String genreName);

    List<Genre> findByIds(List<String> genresId);

    Genre findById(String genreId);

    Genre insert(Genre genre);

    Genre update(String genreId, String genreName);

    void delete(String genreId);
}
