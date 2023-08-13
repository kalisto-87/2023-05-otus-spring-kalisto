package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.converter.GenreConverter;
import ru.otus.domain.Genre;
import ru.otus.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class GenreShellRunner extends AbstractShellComponent {

    private final GenreService genreService;

    private final GenreConverter genreConverter;

    @ShellMethod(value = "find genre by name", key = {"ge-f", "genre-find"})
    public String findByName(@ShellOption String genreName) {
        return Integer.toString(genreService.findByName(genreName).size());
    }

    @ShellMethod(value = "find all genre in the library", key = {"ge-all", "genre-all"})
    public String findAll() {
        String genres = "All genres:";
        for (Genre genre: genreService.getAll()) {
            genres = genres.concat("\n");
            genres = genres.concat(genreConverter.convert(genre));
        }
        return genres;
    }

    @ShellMethod(value = "add new genre to the library", key = "ge-new")
    public String createGenre(@ShellOption String genreName) {
        return String.format("New record has been added into db %s",
                genreConverter.convert(genreService.insert(genreName)));
    }
}
