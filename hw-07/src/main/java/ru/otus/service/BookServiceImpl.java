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

    private static final String LIST_OF_ALL_BOOKS = "List of all books:\n";

    private static final String LIST_OF_ALL_BOOKS_FOUND = "List of the books containing in title '%s':\n%s";

    private static final String LIST_OF_BOOKS_BY_AUTHOR = "List of the books belong to author '%s':\n%s";

    private static final String LIST_OF_BOOKS_BY_GENRE = "List of the books belong to genre '%s':\n%s";

    private static final String CREATED_BOOK = "Book has been created with id=%s";

    private static final String CHANGE_SUCCESSFUL = "Сhanges have been successfully implemented";

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
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(bookName);
        return String.format(LIST_OF_ALL_BOOKS_FOUND, bookName, books.stream().map(book -> bookConverter.convert(book)).
                collect(Collectors.joining("\n")));
    }

    @Override
    public String findByAuthorId(long authorId) {
        Author author = authorRepository.findById(authorId).
                orElseThrow(() -> new DataNotFoundException("Author not found"));
        List<Book> books = bookRepository.findByAuthors(author);
        return String.format(LIST_OF_BOOKS_BY_AUTHOR, author.getName(),
                books.stream().map(book -> bookConverter.convert(book)).
                collect(Collectors.joining("\n")));
    }

    @Override
    public String findByGenreId(long genreId) {
        Genre genre = genreRepository.findById(genreId).
                orElseThrow(() -> new DataNotFoundException("Genre not found"));
        List<Book> books = bookRepository.findByGenres(genre);
        return String.format(LIST_OF_BOOKS_BY_GENRE, genre.getName(),
                books.stream().map(book -> bookConverter.convert(book)).
                collect(Collectors.joining("\n")));
    }

    public String insert(String bookName, List<Long> authorsId, List<Long> genresId) {
        List<Author> authors = authorRepository.findAllById(authorsId);
        List<Genre> genres = genreRepository.findAllById(genresId);
        if (authors.size() == 0) {
            throw new DataNotFoundException("Author not found");
        }
        if (genres.size() == 0) {
            throw new DataNotFoundException("Genre not found");
        }
        Book book = new Book(0, bookName, authors, genres);
        return String.format(CREATED_BOOK, bookRepository.save(book).getId());
    }

    public String update(long bookId, String bookName) {
        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new DataNotFoundException(String.format("Book with id=%s not found!", bookId)));
        book.setTitle(bookName);
        bookRepository.save(book);
        return CHANGE_SUCCESSFUL;
    }

    @Override
    public String delete(long bookId) {
        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new DataNotFoundException(String.format("Book with id=%s not found!")));
        bookRepository.deleteById(bookId);
        return CHANGE_SUCCESSFUL;
    }
}
