package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.converter.AuthorConverter;
import ru.otus.service.AuthorService;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellRunner extends AbstractShellComponent {

    private static final String AUTHOR_NOT_FOUND = "Author with id = %s not found";

    private static final String CHANGES_SUCCESS = "Successfully changed";

    private static final String RECORD_NOT_CHANGED = "The record hasn't been changed";

    private final AuthorService authorService;

    private final AuthorConverter authorConverter;

    @ShellMethod(value = "find author by name", key = {"au-f", "author-find"})
    public String findByName(@ShellOption String authorName) {
        String authors = String.format("List of authors that contain '%s'\n", authorName);
        authors = authors.concat(authorService.findByName(authorName).stream().
                map(author -> authorConverter.convert(author)).
                collect(Collectors.joining("\n")));
        return authors;
    }

    @ShellMethod(value = "find all authors in the library", key = {"au-all", "author-all"})
    public String findAll() {
        String authors = "List of authors:\n";
        authors = authors.concat(authorService.getAll().stream().
                map(author -> authorConverter.convert(author)).
                collect(Collectors.joining("\n")));
        return authors;
    }

    @ShellMethod(value = "add new author to the library", key = {"au-n", "author-new"})
    public String createAuthor(@ShellOption String authorName) {
        return String.format("New record has been added into db %s",
                authorConverter.convert(authorService.insert(authorName)));
    }

    @ShellMethod(value = "update an author by ID", key = {"au-u", "author-update"})
    public String updateAuthor(@ShellOption Long id, @ShellOption String authorName) {
        if (authorService.findById(id) == null) {
            return String.format(AUTHOR_NOT_FOUND, Long.toString(id));
        } else {
            if (authorService.update(id, authorName)) {
                return CHANGES_SUCCESS;
            } else {
                return RECORD_NOT_CHANGED;
            }
        }
    }

    @ShellMethod(value = "delete an author by ID", key = {"au-d", "author-delete"})
    public String deleteAuthor(@ShellOption Long id) {
        if (!authorService.delete(id)) {
            return String.format(AUTHOR_NOT_FOUND, Long.toString(id));
        }
        return CHANGES_SUCCESS;
    }
}
