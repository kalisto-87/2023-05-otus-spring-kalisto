package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.service.transfer.BookConvertToUI;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookShellRunner {

    private final BookConvertToUI convert;

    @ShellMethod(value = "get all books in the library", key = {"b-a", "book-all"})
    public String getAll() {
        return convert.findAll();
    }

    @ShellMethod(value = "get all books in the library", key = {"b-f", "book-find"})
    public String getBooksByName(@ShellOption String name) {
        return convert.findByName(name);
    }

    @ShellMethod(value = "insert new book", key = {"b-n", "book-new"})
    public String insertBook(@ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        return convert.insert(name, List.of(authorId), List.of(genreId));
    }

    @ShellMethod(value = "update book", key = {"b-u", "book-update"})
    public String updateBook(@ShellOption long id, @ShellOption String name) {
        return convert.update(id, name);
    }

    @ShellMethod(value = "delete book by id", key = {"b-d", "book-delete"})
    public String deleteBook(@ShellOption long id) {
        return convert.delete(id);
    }
}
