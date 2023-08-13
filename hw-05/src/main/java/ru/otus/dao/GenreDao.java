package ru.otus.dao;

import ru.otus.domain.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();

    List<Genre> findByName(String genreName);

    Genre findById(Long genreId);

    Genre insertGenre(String genreName);

    boolean updateGenre(Long genreId, String genreName);

    boolean deleteGenre(Long genreId);
}
