package ru.otus.service.transform;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.converter.GenreConverter;
import ru.otus.domain.Genre;
import ru.otus.service.GenreService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreConvertToUIImpl implements GenreConvertToUI {

    private static final String INSERT_RECORD = "New record has been created in the library %s";

    private static final String UPDATE_RECORD = "The record with id=%s has been updated";

    private static final String DELETE_RECORD = "The record with id=%s has been deleted from the library";

    private static final String LIST_OF_ALL_GENRE = "List of all genres:\n";

    private static final String LIST_OF_ALL_GENRE_FOUND = "List of the genres containing '%s':\n%s";

    private final GenreService genreService;

    private final GenreConverter genreConverter;

    @Override
    public String findAll() {
        return LIST_OF_ALL_GENRE +
                genreService.findAll().stream().map(genre -> genreConverter.convert(genre)).
                        collect(Collectors.joining("\n"));
    }

    @Override
    public String findByName(String genreName) {
        return String.format(LIST_OF_ALL_GENRE_FOUND, genreName,
                genreService.findByName(genreName).stream().map(genre -> genreConverter.convert(genre)).
                        collect(Collectors.joining("\n")));
    }

    @Override
    public String insert(String genreName) {
        Genre genre = new Genre(genreName);
        genre = genreService.insert(genre);
        return String.format(INSERT_RECORD, genreConverter.convert(genre));
    }

    @Override
    public String update(String genreId, String genreName) {
        try {
            genreService.update(genreId, genreName);
            return String.format(UPDATE_RECORD, genreId);
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }

    @Override
    public String delete(String genreId) {
        try {
            genreService.delete(genreId);
            return String.format(DELETE_RECORD, genreId);
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }
}
