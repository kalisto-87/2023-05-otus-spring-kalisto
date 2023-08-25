package ru.otus.service;

import ru.otus.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();

    List<Genre> findByName(String genreName);

    List<Genre> findByIds(List<Long> genresId);

    Genre findById(long genreId);

    Genre insert(Genre genre);

    Genre update(long genreId, String genreName);

    void delete(long genreId);
}
