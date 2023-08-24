package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.service.transfer.GenreConvertToUI;

@ShellComponent
@RequiredArgsConstructor
public class GenreShellRunner {

    private final GenreConvertToUI convert;

    @ShellMethod(value = "get all genres in the library", key = {"ge-a", "genre-all"})
    public String getAll() {
        return convert.findAll();
    }

    @ShellMethod(value = "get all genres by name", key = {"ge-f", "genre-find"})
    public String getFindByName(@ShellOption String name) {
        return convert.findByName(name);
    }

    @ShellMethod(value = "insert new genre", key = {"ge-n", "genre-new"})
    public String insertGenre(String name) {
        return convert.insert(name);
    }

    @ShellMethod(value = "update genre", key = {"ge-u", "genre-update"})
    public String updateGenre(@ShellOption long id, @ShellOption String name) {
        return convert.update(id, name);
    }

    @ShellMethod(value = "delete genre by id", key = {"ge-d", "genre-delete"})
    public String deleteGenre(@ShellOption long id) {
        return convert.delete(id);
    }
}
