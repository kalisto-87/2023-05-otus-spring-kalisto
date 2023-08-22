package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.service.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookShellRunner {

    private final BookService bookService;

    @ShellMethod(value = "get all books in the library", key = {"b-a", "book-all"})
    public String getAll() {
        return bookService.findAll();
    }

    @ShellMethod(value = "get all books in the library", key = {"b-f", "book-find"})
    public String getBooksByName(@ShellOption String name) {
        return bookService.findByName(name);
    }

    @ShellMethod(value = "get all books in the library by author", key = {"b-f-a", "book-find-author"})
    public String getBooksByAuthor(@ShellOption long id) {
        return bookService.findByAuthorId(id);
    }

    @ShellMethod(value = "get all books in the library by genre", key = {"b-f-g", "book-find-genre"})
    public String getBooksByGenre(@ShellOption long id) {
        return bookService.findByGenreId(id);
    }

    @ShellMethod(value = "insert new book", key = {"b-n", "book-new"})
    public String insertBook(@ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        bookService.insert(name, List.of(authorId), List.of(genreId));
        return "";
    }

    @ShellMethod(value = "update book", key = {"b-u", "book-update"})
    public String updateBook(@ShellOption long id, @ShellOption String name) {
        bookService.update(id, name);
        return "";
    }

    @ShellMethod(value = "delete book by id", key = {"b-d", "book-delete"})
    public String deleteBook(@ShellOption long id) {
        bookService.delete(id);
        return "";
    }
}
