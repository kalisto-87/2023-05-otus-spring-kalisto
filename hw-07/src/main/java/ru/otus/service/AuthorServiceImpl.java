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
    public Author insert(String authorName) {
        Author author = new Author(0, authorName);
        return authorRepository.save(author);
    }

    @Override
    public Author update(long authorId, String authorName) {
        Author author = authorRepository.findById(authorId).
                orElseThrow(() -> new DataNotFoundException(String.format("Author with id=%s not found!", authorId)));
        author.setName(authorName);
        return authorRepository.save(author);
    }

    @Override
    public void delete(long authorId) {
        Author author = authorRepository.findById(authorId).
                orElseThrow(() -> new DataNotFoundException(String.format("Author with id=%s not found!", authorId)));
        authorRepository.deleteById(authorId);
    }
}
