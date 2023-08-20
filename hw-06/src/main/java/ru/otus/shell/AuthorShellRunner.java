package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.converter.AuthorConverter;
import ru.otus.domain.Author;
import ru.otus.repository.AuthorRepository;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellRunner {

    private final AuthorRepository authorRepository;

    private final AuthorConverter authorConverter;

    @ShellMethod(value = "get all authors in the library", key = "au-a")
    public String getAll() {
        return authorRepository.findAll().stream().map(author -> authorConverter.convert(author)).
                collect(Collectors.joining("\n"));
    }

    @ShellMethod(value = "get all authors by name", key = "au-f")
    public String getFindByName(String name) {
        return "";
    }

    @ShellMethod(value = "insert new author", key = "au-n")
    public String insertAuthor(String name) {
        Author author = new Author(0, name);
        return authorConverter.convert(authorRepository.insert(author));
    }

    @ShellMethod(value = "update author", key = "au-u")
    public String updateAuthor(long id, String name) {
        return "";
    }

    @ShellMethod(value = "delete author by id", key = "au-d")
    public String deleteAuthor(long id) {
        return "";
    }
}
