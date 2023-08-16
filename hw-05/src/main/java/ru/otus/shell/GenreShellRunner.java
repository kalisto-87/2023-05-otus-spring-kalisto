package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.converter.GenreConverter;
import ru.otus.service.GenreService;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class GenreShellRunner extends AbstractShellComponent {

    private static final String GENRE_NOT_FOUND = "Genre with id = %s not found";

    private static final String CHANGES_SUCCESS = "Successfully changed";

    private static final String RECORD_NOT_CHANGED = "The record hasn't been changed";

    private final GenreService genreService;

    private final GenreConverter genreConverter;

    @ShellMethod(value = "find genre by name", key = {"ge-f", "genre-find"})
    public String findByName(@ShellOption String genreName) {
        String genres = String.format("All genres containing '%s':\n", genreName);
        genres = genres.concat(genreService.findByName(genreName).stream().
                map(genre -> genreConverter.convert(genre)).
                collect(Collectors.joining("\n")));
        return genres;
    }

    @ShellMethod(value = "find all genre in the library", key = {"ge-all", "genre-all"})
    public String findAll() {
        String genres = "All genres:\n";
        genres = genres.concat(genreService.getAll().stream().
                map(genre -> genreConverter.convert(genre)).
                collect(Collectors.joining("\n")));
        return genres;
    }

    @ShellMethod(value = "add new genre to the library", key = "ge-new")
    public String createGenre(@ShellOption String genreName) {
        return String.format("New record has been added into db %s",
                genreConverter.convert(genreService.insert(genreName)));
    }

    @ShellMethod(value = "update a genre by ID", key = {"ge-u", "genre-update"})
    public String updateGenre(@ShellOption Long id, @ShellOption String genreName) {
        if (genreService.findById(id) == null) {
            return String.format(GENRE_NOT_FOUND, id);
        } else {
            if (genreService.update(id, genreName)) {
                return CHANGES_SUCCESS;
            } else {
                return RECORD_NOT_CHANGED;
            }
        }
    }

    @ShellMethod(value = "delete a genre by ID", key = {"ge-d", "genre-delete"})
    public String deleteGenre(@ShellOption Long id) {
        if (!genreService.delete(id)) {
            return String.format(GENRE_NOT_FOUND, id);
        }
        return CHANGES_SUCCESS;
    }
}
