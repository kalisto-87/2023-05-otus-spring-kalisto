package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final BookRepository bookRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findByName(String authorName) {
        return authorRepository.findByNameContainingIgnoreCase(authorName);
    }

    @Override
    public List<Author> findByIds(List<String> authorsId) {
        return authorsId.stream().map(m -> authorRepository.findById(m).orElseThrow(
                () -> new DataNotFoundException(String.format("Author with id=%s not found", m))
        )).toList();
    }

    @Override
    public Author findById(String authorId) {
        return authorRepository.findById(authorId).orElseThrow(
                () -> new DataNotFoundException(String.format("Author with id=%s not found", authorId)));
    }

    @Override
    public Author insert(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(String authorId, String authorName) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new DataNotFoundException(String.format("Author with id=%s not found", authorId)));
        author.setName(authorName);
        List<Book> books = bookRepository.findBookByAuthorsId(authorId);
        books.forEach(b -> b.getAuthors().forEach(a -> a.setName(authorName)));
        books.forEach(b -> bookRepository.save(b));
        return authorRepository.save(author);
    }

    @Override
    public void delete(String authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new DataNotFoundException(String.format("Author with id=%s not found", authorId)));
        List<Book> books = bookRepository.findBookByAuthorsId(authorId);
        if (books.size() > 0) {
            throw new DataNotFoundException("Operation is not acceptable. There are the books belonging to this author");
        }
        authorRepository.delete(author);
    }
}
