package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.service.transform.AuthorConvertToUI;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellRunner {

    private final AuthorConvertToUI authorConvertToUI;

    @ShellMethod(value = "get all authors in the library", key = {"au-a", "author-all"})
    public String getAll() {
        return authorConvertToUI.findAll();
    }

    @ShellMethod(value = "get all authors by name", key = {"au-f", "author-find"})
    public String getFindByName(@ShellOption String name) {
        return authorConvertToUI.findByName(name);
    }

    @ShellMethod(value = "insert new author", key = {"au-n", "author-new"})
    public String insertAuthor(String name) {
        return authorConvertToUI.insert(name);
    }

    @ShellMethod(value = "update author", key = {"au-u", "author-update"})
    public String updateAuthor(@ShellOption String id, @ShellOption String name) {
        return authorConvertToUI.update(id, name);
    }

    @ShellMethod(value = "delete author by id", key = {"au-d", "author-delete"})
    public String deleteAuthor(@ShellOption String id) {
        return authorConvertToUI.delete(id);
    }
}
