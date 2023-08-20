package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.converter.BookConverter;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private static final String LIST_OF_ALL_BOOKS = "Список всех книг:\n";

    private static final String LIST_OF_ALL_BOOKS_FOUND = "Список книг, содержащих в наименовании '%s':\n %s";

    private final BookRepository bookRepository;

    private final BookConverter bookConverter;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    public String findAll() {
        List<Book> books = bookRepository.findAll();
        for (Book b: books) {
            List<Genre> genres = genreRepository.findByBook(b.getId());
            List<Author> authors = authorRepository.findByBook(b.getId());
            b.setGenres(genres);
            b.setAuthors(authors);
        }
        return LIST_OF_ALL_BOOKS + books.stream().map(book -> bookConverter.convert(book)).
                collect(Collectors.joining("\n"));
    }

    @Override
    public String findByName(String bookName) {
        List<Book> books = bookRepository.findByName(bookName);
        for (Book b: books) {
            List<Genre> genres = genreRepository.findByBook(b.getId());
            List<Author> authors = authorRepository.findByBook(b.getId());
            b.setGenres(genres);
            b.setAuthors(authors);
        }
        return String.format(LIST_OF_ALL_BOOKS_FOUND, bookName, books.stream().map(book -> bookConverter.convert(book)).
                collect(Collectors.joining("\n")));
    }
}
