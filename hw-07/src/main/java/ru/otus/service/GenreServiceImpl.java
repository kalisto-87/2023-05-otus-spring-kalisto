package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.converter.GenreConverter;
import ru.otus.domain.Genre;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.GenreRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private static final String LIST_OF_ALL_GENRE = "List of all genres:\n";

    private static final String LIST_OF_ALL_GENRE_FOUND = "List of the genres containing '%s':\n%s";

    private static final String CREATED_GENRE = "Genre has been created with id=%s";

    private static final String CHANGE_SUCCESSFUL = "Сhanges have been successfully implemented";

    private final GenreRepository genreRepository;

    private final GenreConverter genreConverter;

    @Override
    public String findAll() {
        return LIST_OF_ALL_GENRE +
                genreRepository.findAll().stream().map(genre -> genreConverter.convert(genre)).
                        collect(Collectors.joining("\n"));
    }

    @Override
    public String findByName(String genreName) {
        return String.format(LIST_OF_ALL_GENRE_FOUND, genreName,
                genreRepository.findByNameContainingIgnoreCase(genreName).stream().
                        map(genre -> genreConverter.convert(genre)).
                        collect(Collectors.joining("\n")));
    }

    @Override
    public String insert(String genreName) {
        Genre genre = new Genre(0, genreName);
        genre = genreRepository.save(genre);
        return String.format(CREATED_GENRE, genre.getId());
    }

    @Override
    public String update(long genreId, String genreName) {
        Genre genre = genreRepository.findById(genreId).
                orElseThrow(() -> new DataNotFoundException(String.format("Genre with id=%s not found!", genreId)));
        genre.setName(genreName);
        return CHANGE_SUCCESSFUL;
    }

    @Override
    public String delete(long genreId) {
        Genre genre = genreRepository.findById(genreId).
                orElseThrow(() -> new DataNotFoundException(String.format("Genre with id=%s not found!", genreId)));
        genreRepository.deleteById(genreId);
        return CHANGE_SUCCESSFUL;
    }
}
