package ru.otus.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dto.GenreDto;

import java.util.List;

public interface GenreService {
    Flux<GenreDto> findAll();

    Flux<GenreDto> findByName(String genreName);

    Flux<GenreDto> findByIds(List<String> genresId);

    Mono<GenreDto> findById(String genreId);

    Mono<GenreDto> insert(GenreDto genreDto);

    Mono<GenreDto> update(GenreDto genreDto);

    Mono<Void> delete(String genreId);
}
