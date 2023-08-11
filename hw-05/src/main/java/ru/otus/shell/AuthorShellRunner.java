package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.domain.Author;
import ru.otus.service.AuthorService;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellRunner extends AbstractShellComponent {

    private final AuthorService authorService;

    @ShellMethod(value = "find author by Id", key = {"f", "find"})
    public Author finBydId(@ShellOption Long authorId) {
        return new Author(authorId, "Noname");
    }

}
