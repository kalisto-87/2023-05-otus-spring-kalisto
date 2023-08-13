package ru.otus.service;

import ru.otus.domain.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    List<Genre> findByName(String genreName);

    Genre findById(Long genreId);

    Genre insert(String genreName);

    boolean update(Long genreId, String genreName);

    boolean delete(Long genreId);

}
