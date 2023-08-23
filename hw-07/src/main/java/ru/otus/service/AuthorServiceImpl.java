package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.converter.AuthorConverter;
import ru.otus.domain.Author;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.AuthorRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private static final String LIST_OF_ALL_AUTHORS = "List of all authors:\n";

    private static final String LIST_OF_ALL_AUTHORS_FOUND = "List of the authors containing '%s':\n%s";

    private static final String CHANGE_SUCCESSFUL = "Сhanges have been successfully implemented";

    private static final String CREATED_AUTHOR = "Author has been created with id=%s";

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
                authorRepository.findByNameContainingIgnoreCase(authorName).
                        stream().map(author -> authorConverter.convert(author)).
                        collect(Collectors.joining("\n")));
    }

    @Override
    public String insert(String authorName) {
        Author author = new Author(0, authorName);
        author = authorRepository.save(author);
        return String.format(CREATED_AUTHOR, author.getId());
    }

    @Override
    public String update(long authorId, String authorName) {
        Author author = authorRepository.findById(authorId).
                orElseThrow(() -> new DataNotFoundException(String.format("Author with id=%s not found!", authorId)));
        author.setName(authorName);
        authorRepository.save(author);
        return CHANGE_SUCCESSFUL;
    }

    @Override
    public String delete(long authorId) {
        Author author = authorRepository.findById(authorId).
                orElseThrow(() -> new DataNotFoundException(String.format("Author with id=%s not found!", authorId)));
        authorRepository.deleteById(authorId);
        return CHANGE_SUCCESSFUL;
    }
}
