package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Author;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findByName(String authorName) {
        return authorRepository.findByNameContainingIgnoreCase(authorName);
    }

    @Override
    public List<Author> findByIds(List<Long> authorsId) {
        List<Author> authors = authorsId.stream().map(m -> authorRepository.findById(m).orElseThrow(
                () -> new DataNotFoundException(String.format("Author with id=%s not found", m))
        )).toList();
        return authors;
    }

    @Override
    public Author findById(long authorId) {
        return authorRepository.findById(authorId).orElseThrow(
                () -> new DataNotFoundException(String.format("Author with id=%s not found", authorId)));
    }

    @Override
    public Author insert(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(long authorId, String authorName) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new DataNotFoundException(String.format("Author with id=%s not found", authorId)));
        author.setName(authorName);
        return authorRepository.save(author);
    }

    @Override
    public void delete(long authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new DataNotFoundException(String.format("Author with id=%s not found", authorId)));
        authorRepository.delete(author);
    }
}
