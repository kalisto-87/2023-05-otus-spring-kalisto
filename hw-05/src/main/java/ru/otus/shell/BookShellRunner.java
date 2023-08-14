package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.converter.BookConverter;
import ru.otus.domain.Author;
import ru.otus.domain.Genre;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;

import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class BookShellRunner extends AbstractShellComponent {

    private static final String BOOK_NOT_FOUND = "Book with id = %s not found";

    private static final String CHANGES_SUCCESS = "Successfully changed";

    private final BookService bookService;

    private final AuthorService authorService;

    private final BookConverter bookConverter;

    private final GenreService genreService;

    @ShellMethod(value = "find all books in the library", key = {"b-all", "book-all"})
    public String findAll() {
        String books = "List of books:\n";
        books = books.concat(bookService.getAll().stream().
                map(book -> bookConverter.convert(book)).
                collect(Collectors.joining("\n")));
        return books;
    }

    @ShellMethod(value = "find all books in the library by name", key = {"b-f", "book-find"})
    public String findByName(String name) {
        String books = String.format("List of books containing %s", name);
        books = books.concat(bookService.findByName(name).stream().
                map(book -> bookConverter.convert(book)).
                collect(Collectors.joining("\n")));
        return books;
    }

    @ShellMethod(value = "find all books in the library by author", key = {"b-f-a", "book-find-by-author"})
    public String findByAuthor(long AuthorId) {
        Author author = authorService.findById(AuthorId);
        String books = String.format("List of books written by %s:\n", author.getName());
        books = books.concat(bookService.findByAuthor(author).stream().
                map(book -> bookConverter.convert(book)).
                collect(Collectors.joining("\n")));
        return books;
    }

    @ShellMethod(value = "find all books in the library by genre", key = {"b-f-g", "book-find-by-genre"})
    public String findByGenre(long genreId) {
        Genre genre = genreService.findById(genreId);
        String books = String.format("List of books genre of %s:\n", genre.getName());
        books = books.concat(bookService.findByGenre(genre).stream().
                map(book -> bookConverter.convert(book)).
                collect(Collectors.joining("\n")));
        return books;
    }

    @ShellMethod(value = "add new book to the library", key = {"b-n", "book-new"})
    public String createBook(@ShellOption String bookName,
                             @ShellOption Long authorId,
                             @ShellOption Long genreId) {
        return String.format("New record has been added into db %s",
                bookConverter.convert(bookService.insert(bookName, authorId, genreId)));
    }

    @ShellMethod(value = "delete a book by ID", key = {"b-d", "book-delete"})
    public String deleteBook(@ShellOption Long id) {
        if (!bookService.deleteBook(id)) {
            return String.format(BOOK_NOT_FOUND, id);
        }
        return CHANGES_SUCCESS;
    }
}
