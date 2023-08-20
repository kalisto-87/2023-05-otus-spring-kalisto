package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.BookService;

@ShellComponent
@RequiredArgsConstructor
public class BookShellRunner {

    private final BookService bookService;

    @ShellMethod(value = "get all books in the library", key = {"b-a", "book-all"})
    public String getAll() {
        return bookService.findAll();
    }

}
