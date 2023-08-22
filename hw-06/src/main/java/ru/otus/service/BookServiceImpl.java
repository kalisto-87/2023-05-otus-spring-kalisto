package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.converter.BookConverter;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private static final String INSERT_RECORD = "New record has been created in the library %s";

    private static final String UPDATE_RECORD = "The record with id=%s has been updated";

    private static final String DELETE_RECORD = "The record with id=%s has been deleted from the library";

    private static final String LIST_OF_ALL_BOOKS = "List of all books:\n";

    private static final String LIST_OF_ALL_BOOKS_FOUND = "List of the books containing in title '%s':\n %s";

    private static final String UNSUCCESSFUL_CHANGES = "The record hasn't been changed";

    private final BookRepository bookRepository;

    private final BookConverter bookConverter;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    public String findAll() {
        List<Book> books = bookRepository.findAll();
        return LIST_OF_ALL_BOOKS + books.stream().map(book -> bookConverter.convert(book)).
                collect(Collectors.joining("\n"));
    }

    @Override
    public String findByName(String bookName) {
        List<Book> books = bookRepository.findByName(bookName);
        return String.format(LIST_OF_ALL_BOOKS_FOUND, bookName, books.stream().map(book -> bookConverter.convert(book)).
                collect(Collectors.joining("\n")));
    }

    public String insert(String bookName, List<Long> authorsId, List<Long> genreIds) {
        Book book = new Book(0, bookName, authorRepository.findByIds(authorsId),
                genreRepository.findByIds(genreIds));
        book = bookRepository.insert(book);
        return String.format(INSERT_RECORD, bookConverter.convert(book));
    }

    public String update(long bookId, String bookName) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new DataNotFoundException("Book not found"));
        book.setTitle(bookName);
        if (bookRepository.update(book)) {
            return String.format(UPDATE_RECORD, bookId);
        }
        return UNSUCCESSFUL_CHANGES;
    }

    @Override
    public String delete(long bookId) {
        if (bookRepository.delete(bookId)) {
            return String.format(DELETE_RECORD, bookId);
        }
        return UNSUCCESSFUL_CHANGES;
    }
}
