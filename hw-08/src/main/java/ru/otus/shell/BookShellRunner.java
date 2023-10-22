package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.service.transform.BookConvertToUI;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookShellRunner {

    private final BookConvertToUI bookConvertToUI;

    @ShellMethod(value = "get all books in the library", key = {"b-a", "book-all"})
    public String getAll() {
        return bookConvertToUI.findAll();
    }

    @ShellMethod(value = "get all books in the library", key = {"b-f", "book-find"})
    public String getBooksByName(@ShellOption String name) {
        return bookConvertToUI.findByName(name);
    }

    @ShellMethod(value = "get all books in the library by author", key = {"b-f-a", "book-find-author"})
    public String getBooksByAuthor(@ShellOption String id) {
        return bookConvertToUI.findByAuthorId(id);
    }

    @ShellMethod(value = "get all books in the library by genre", key = {"b-f-g", "book-find-genre"})
    public String getBooksByGenre(@ShellOption String id) {
        return bookConvertToUI.findByGenreId(id);
    }

    @ShellMethod(value = "insert new book", key = {"b-n", "book-new"})
    public String insertBook(@ShellOption String name, @ShellOption String authorId, @ShellOption String genreId) {
        return bookConvertToUI.insert(name, List.of(authorId), List.of(genreId));
    }

    @ShellMethod(value = "update book", key = {"b-u", "book-update"})
    public String updateBook(@ShellOption String id, @ShellOption String name) {
        return bookConvertToUI.update(id, name);
    }

    @ShellMethod(value = "delete book by id", key = {"b-d", "book-delete"})
    public String deleteBook(@ShellOption String id) {
        return bookConvertToUI.delete(id);
    }
}
