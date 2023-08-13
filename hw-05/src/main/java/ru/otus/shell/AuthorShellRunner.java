package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.converter.AuthorConverter;
import ru.otus.domain.Author;
import ru.otus.service.AuthorService;

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
        String authors = String.format("List of authors that contain %s", authorName);
        for (Author author: authorService.findByName(authorName)) {
            authors = authors.concat("\n");
            authors = authors.concat(authorConverter.convert(author));
        }
        return authors;
    }

    @ShellMethod(value = "find all authors in the library", key = {"au-all", "author-all"})
    public String findAll() {
        String authors = "List of authors:";
        for (Author author: authorService.getAll()) {
            authors = authors.concat("\n");
            authors = authors.concat(authorConverter.convert(author));
        }
        return authors;
    }

    @ShellMethod(value = "add new author to the library", key = "au-new")
    public String createAuthor(@ShellOption String authorName) {
        return String.format("New record has been added into db %s",
                authorConverter.convert(authorService.insert(authorName)));
    }

    @ShellMethod(value = "update an author by ID", key = "au-update")
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

    @ShellMethod(value = "delete an author by ID", key = "au-delete")
    public String deleteAuthor(@ShellOption Long id) {
        if (!authorService.delete(id)) {
            return String.format(AUTHOR_NOT_FOUND, Long.toString(id));
        }
        return CHANGES_SUCCESS;
    }
}
