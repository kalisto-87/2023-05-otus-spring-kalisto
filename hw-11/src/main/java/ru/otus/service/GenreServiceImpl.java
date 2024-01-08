package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Genre;
import ru.otus.dto.GenreDto;
import ru.otus.exception.DataNotFoundException;
import ru.otus.mapper.GenreMapper;
import ru.otus.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Override
    public Flux<GenreDto> findAll() {
        return genreRepository.findAll().map(g -> genreMapper.toDto(g));
    }

    @Override
    public Flux<GenreDto> findByName(String genreName) {
        return genreRepository.findByNameContainingIgnoreCase(genreName).map(g -> genreMapper.toDto(g));
    }

    @Override
    public Flux<GenreDto> findByIds(List<String> genresId) {

        Flux<GenreDto> genres = null;
        for (String s : genresId
        ) {
            Mono<GenreDto> genre = genreRepository.findById(s).map(g -> genreMapper.toDto(g));
            genres = Flux.merge(genre);
        }
        return genres;
    }

    @Override
    public Mono<GenreDto> findById(String genreId) {
        Mono<Genre> genre = genreRepository.findById(genreId).switchIfEmpty(Mono.error(
                new DataNotFoundException(String.format("Genre with id=%s not found", genreId))));
        return genre.map(g -> genreMapper.toDto(g));
    }

    @Override
    @Transactional
    public Mono<GenreDto> insert(GenreDto genreDto) {
        Genre newGenre = genreMapper.toDomain(genreDto);
        return genreRepository.save(newGenre).map(g -> genreMapper.toDto(g));
    }

    @Override
    @Transactional
    public Mono<GenreDto> update(GenreDto genreDto) {
        Mono<Genre> genre = genreRepository.findById(genreDto.getId()).switchIfEmpty(Mono.error(
                new DataNotFoundException(String.format("Genre with id=%s not found", genreDto.getId()))));
        String name = genreDto.getName();
        if (name != null && !name.isEmpty()) {
            Mono<Genre> newGenre = genre.map(g -> {
                return new Genre(genreDto.getId(), name);
            });
            Genre g = newGenre.block();
            return genreRepository.save(g).map(m -> genreMapper.toDto(m));
        }
        return genre.map(g -> genreMapper.toDto(g));
    }

    @Override
    @Transactional
    public Mono<Void> delete(String genreId) {
        Genre genre = genreRepository.findById(genreId).block();
        return genreRepository.deleteById(genre.getId());
    }
}
