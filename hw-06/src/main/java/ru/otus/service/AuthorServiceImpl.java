package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.converter.AuthorConverter;
import ru.otus.domain.Author;
import ru.otus.repository.AuthorRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private static final String INSERT_RECORD = "New record has been created in the library %s";

    private static final String UPDATE_RECORD = "The record with id=%s has been updated";

    private static final String DELETE_RECORD = "The record with id=%s has been deleted from the library";

    private static final String UNSUCCESSFUL_CHANGES = "The record hasn't been changed";

    private static final String LIST_OF_ALL_AUTHORS = "List of all authors:\n";

    private static final String LIST_OF_ALL_AUTHORS_FOUND = "List of the authors containing '%s':\n %s";

    private final AuthorRepository authorRepository;

    private final AuthorConverter authorConverter;

    @Override
    public String findAll() {
        return LIST_OF_ALL_AUTHORS +
                authorRepository.findAll().stream().map(author -> authorConverter.convert(author)).
                collect(Collectors.joining("\n"));
    }

    @Override
    public String findByName(String authorName) {
        return String.format(LIST_OF_ALL_AUTHORS_FOUND, authorName,
                authorRepository.findByName(authorName).stream().map(author -> authorConverter.convert(author)).
                        collect(Collectors.joining("\n")));
    }

    @Override
    public String insert(String authorName) {
        Author author = new Author(0, authorName);
        author = authorRepository.insert(author);
        return String.format(INSERT_RECORD, authorConverter.convert(author));
    }

    @Override
    public String update(long authorId, String authorName) {
        Author author = new Author(authorId, authorName);
        if (authorRepository.update(author)) {
            return String.format(UPDATE_RECORD, authorId);
        }
        return UNSUCCESSFUL_CHANGES;
    }

    @Override
    public String delete(long authorId) {
        if (authorRepository.delete(authorId)) {
            return String.format(DELETE_RECORD, authorId);
        }
        return UNSUCCESSFUL_CHANGES;
    }
}
