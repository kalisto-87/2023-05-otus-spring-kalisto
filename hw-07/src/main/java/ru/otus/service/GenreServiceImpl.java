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
    public Genre insert(String genreName) {
        Genre genre = new Genre(0, genreName);
        return genreRepository.save(genre);
   }

    @Override
    public Genre update(long genreId, String genreName) {
        Genre genre = genreRepository.findById(genreId).
                orElseThrow(() -> new DataNotFoundException(String.format("Genre with id=%s not found!", genreId)));
        genre.setName(genreName);
        return genreRepository.save(genre);
    }

    @Override
    public void delete(long genreId) {
        Genre genre = genreRepository.findById(genreId).
                orElseThrow(() -> new DataNotFoundException(String.format("Genre with id=%s not found!", genreId)));
        genreRepository.deleteById(genreId);
    }
}
