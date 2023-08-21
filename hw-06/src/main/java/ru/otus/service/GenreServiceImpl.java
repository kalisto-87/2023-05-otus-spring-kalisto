package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.converter.GenreConverter;
import ru.otus.domain.Genre;
import ru.otus.repository.GenreRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private static final String INSERT_RECORD = "New record has been created in the library %s";

    private static final String UPDATE_RECORD = "The record with id=%s has been updated";

    private static final String DELETE_RECORD = "The record with id=%s has been deleted from the library";

    private static final String UNSUCCESSFUL_CHANGES = "The record hasn't been changed";

    private static final String LIST_OF_ALL_GENRE = "List of all genres:\n";

    private static final String LIST_OF_ALL_GENRE_FOUND = "List of the genres containing '%s':\n %s";

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
                genreRepository.findByName(genreName).stream().map(genre -> genreConverter.convert(genre)).
                        collect(Collectors.joining("\n")));
    }

    @Override
    public String insert(String genreName) {
        Genre genre = new Genre(0, genreName);
        genre = genreRepository.insert(genre);
        return String.format(INSERT_RECORD, genreConverter.convert(genre));
    }

    @Override
    public String update(long genreId, String genreName) {
        Genre genre = new Genre(genreId, genreName);
        if (genreRepository.update(genre)) {
            return String.format(UPDATE_RECORD, genreId);
        }
        return UNSUCCESSFUL_CHANGES;
    }

    @Override
    public String delete(long genreId) {
        if (genreRepository.delete(genreId)) {
            return String.format(DELETE_RECORD, genreId);
        }
        return UNSUCCESSFUL_CHANGES;
    }
}
