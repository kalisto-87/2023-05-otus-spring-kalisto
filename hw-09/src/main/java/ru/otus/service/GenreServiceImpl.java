package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Genre;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> findByName(String genreName) {
        return genreRepository.findByNameContainingIgnoreCase(genreName);
    }

    @Override
    public List<Genre> findByIds(List<Long> genresId) {
        List<Genre> genres = genresId.stream().map(m -> genreRepository.findById(m).orElseThrow(
                () -> new DataNotFoundException(String.format("Genre with id=%s not found", m))
        )).toList();
        return genres;
    }

    @Override
    public Genre findById(long genreId) {
        return genreRepository.findById(genreId).orElseThrow(
                () -> new DataNotFoundException(String.format("Genre with id=%s not found", genreId)));
    }

    @Override
    @Transactional
    public Genre insert(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    public Genre update(long genreId, String genreName) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(
                () -> new DataNotFoundException(String.format("Genre with id=%s not found", genreId)));
        genre.setName(genreName);
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    public void delete(long genreId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(
                () -> new DataNotFoundException(String.format("Genre with id=%s not found", genreId)));
        genreRepository.delete(genre);
    }
}
