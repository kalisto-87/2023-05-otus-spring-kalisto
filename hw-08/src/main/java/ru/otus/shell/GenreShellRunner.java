package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.service.transform.GenreConvertToUI;

@ShellComponent
@RequiredArgsConstructor
public class GenreShellRunner {

    private final GenreConvertToUI genreConvertToUI;

    @ShellMethod(value = "get all genres in the library", key = {"ge-a", "genre-all"})
    public String getAll() {
        return genreConvertToUI.findAll();
    }

    @ShellMethod(value = "get all genres by name", key = {"ge-f", "genre-find"})
    public String getFindByName(@ShellOption String name) {
        return genreConvertToUI.findByName(name);
    }

    @ShellMethod(value = "insert new genre", key = {"ge-n", "genre-new"})
    public String insertGenre(String name) {
        return genreConvertToUI.insert(name);
    }

    @ShellMethod(value = "update genre", key = {"ge-u", "genre-update"})
    public String updateGenre(@ShellOption String id, @ShellOption String name) {
        return genreConvertToUI.update(id, name);
    }

    @ShellMethod(value = "delete genre by id", key = {"ge-d", "genre-delete"})
    public String deleteGenre(@ShellOption String id) {
        return genreConvertToUI.delete(id);
    }
}
