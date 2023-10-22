package ru.otus.service.transform;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.converter.BookConverter;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookConvertToUIImpl implements BookConvertToUI {

    private static final String INSERT_RECORD = "New record has been created in the library %s";

    private static final String UPDATE_RECORD = "The record with id=%s has been updated";

    private static final String DELETE_RECORD = "The record with id=%s has been deleted from the library";

    private static final String LIST_OF_ALL_BOOKS = "List of all books:\n";

    private static final String LIST_OF_ALL_BOOKS_FOUND = "List of the books containing in title '%s':\n%s";

    private static final String LIST_BOOKS_AUTHOR = "List of the books belonging to author '%s':\n%s";

    private static final String LIST_BOOKS_GENRE = "List of the books belonging to genre '%s':\n%s";

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final BookConverter bookConverter;

    @Override
    public String findAll() {
        List<Book> books = bookService.findAll();
        return LIST_OF_ALL_BOOKS + books.stream().map(book -> bookConverter.convert(book)).
                collect(Collectors.joining("\n"));
    }

    @Override
    public String findByName(String bookName) {
        List<Book> books = bookService.findByName(bookName);
        return String.format(LIST_OF_ALL_BOOKS_FOUND, bookName, books.stream().map(book -> bookConverter.convert(book)).
                collect(Collectors.joining("\n")));
    }

    @Override
    public String findByAuthorId(String authorId) {
        try {
            Author author = authorService.findById(authorId);
            List<Book> books = bookService.findByAuthorId(authorId);
            return String.format(LIST_BOOKS_AUTHOR, author.getName(),
                    books.stream().map(book -> bookConverter.convert(book)).
                            collect(Collectors.joining("\n")));
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }

    @Override
    public String findByGenreId(String genreId) {
        try {
            Genre genre = genreService.findById(genreId);
            List<Book> books = bookService.findByGenreId(genreId);
            return String.format(LIST_BOOKS_GENRE, genre.getName(),
                    books.stream().map(book -> bookConverter.convert(book)).
                            collect(Collectors.joining("\n")));
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }

    @Override
    public String insert(String bookName, List<String> authors, List<String> genres) {
        Book book = new Book(bookName, authorService.findByIds(authors), genreService.findByIds(genres));
        book = bookService.insert(book);
        return String.format(INSERT_RECORD, bookConverter.convert(book));
    }

    @Override
    public String update(String bookId, String bookName) {
        try {
            bookService.update(bookId, bookName);
            return String.format(UPDATE_RECORD, bookId);
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }

    @Override
    public String delete(String bookId) {
        try {
            bookService.delete(bookId);
            return String.format(DELETE_RECORD, bookId);
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }
}
