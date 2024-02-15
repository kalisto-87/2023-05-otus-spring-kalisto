package ru.otus.service;

import ru.otus.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> findAll();

    List<GenreDto> findByName(String genreName);

    List<GenreDto> findByIds(List<Long> genresId);

    GenreDto findById(long genreId);

    GenreDto insert(GenreDto genreDto);

    GenreDto update(GenreDto genreDto);

    void delete(long genreId);
}
