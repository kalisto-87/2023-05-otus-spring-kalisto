package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.h2.Author;
import ru.otus.domain.h2.Book;
import ru.otus.domain.h2.Genre;
import ru.otus.domain.mongo.MongoAuthor;
import ru.otus.domain.mongo.MongoBook;
import ru.otus.domain.mongo.MongoGenre;
import ru.otus.repository.AuthorRepositoryJpa;
import ru.otus.repository.BookRepositoryJpa;
import ru.otus.repository.GenreRepositoryJpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final BookRepositoryJpa repositoryBook;

    private final AuthorRepositoryJpa repositoryAuthor;

    private final GenreRepositoryJpa repositoryGenre;

    private final Map<String, Long> authorMap = new HashMap<>();

    private final Map<String, Long> genreMap = new HashMap<>();

    public Author transferAuthor(MongoAuthor author) {
        Long id = repositoryAuthor.getIdNextVal();
        authorMap.put(author.getId(), id);
        return new Author(id, author.getName());
    }

    public Genre transferGenre(MongoGenre genre) {
        Long id = repositoryGenre.getIdNextVal();
        genreMap.put(genre.getId(), id);
        return new Genre(id, genre.getName());
    }

    public Book transferBook(MongoBook book) {
        List<Author> authors = new ArrayList<>();
        for (MongoAuthor a: book.getAuthors()
             ) {
            authors.add(new Author(authorMap.get(a.getId()), a.getName()));
        }
        List<Genre> genres = new ArrayList<>();
        for (MongoGenre g: book.getGenres()
        ) {
            genres.add(new Genre(genreMap.get(g.getId()), g.getName()));
        }
        return new Book(repositoryBook.getIdNextVal(), book.getTitle(), authors, genres);
    }
}
