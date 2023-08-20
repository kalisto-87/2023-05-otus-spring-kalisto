package ru.otus.repository;

import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    List<Genre> findAll();

    Optional<Genre> findById(long genreId);

    List<Genre> findByName(String genreName);

    Genre insert(Genre genre);

    boolean update(Genre genre);

    boolean delete(long genreId);
}
