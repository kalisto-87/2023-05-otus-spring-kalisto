package ru.otus.repository;

import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    List<Genre> findAll();

    Optional<Genre> findById(long genreId);

    List<Genre> findByName(String genreName);

    Genre insert(Genre genre);

    Genre update(Genre genre);

    void delete(Genre genre);

    List<Genre> findByBook(long bookId);

    List<Genre> findByIds(List<Long> ids);
}
