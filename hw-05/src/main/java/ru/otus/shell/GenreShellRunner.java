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
}
