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

    @ShellMethod(value = "insert new book", key = {"b-n", "book-new"})
    public String insertAuthor(@ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        return bookService.insert(name, List.of(authorId), List.of(genreId));
    }
}
