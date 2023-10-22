package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.BookRepository;
import ru.otus.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> findByName(String genreName) {
        return genreRepository.findByNameContainingIgnoreCase(genreName);
    }

    @Override
    public List<Genre> findByIds(List<String> genresId) {
        List<Genre> genres = genresId.stream().map(m -> genreRepository.findById(m).orElseThrow(
                () -> new DataNotFoundException(String.format("Genre with id=%s not found", m))
        )).toList();
        return genres;
    }

    @Override
    public Genre findById(String genreId) {
        return genreRepository.findById(genreId).orElseThrow(
                () -> new DataNotFoundException(String.format("Genre with id=%s not found", genreId)));
    }

    @Override
    @Transactional
    public Genre insert(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    public Genre update(String genreId, String genreName) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(
                () -> new DataNotFoundException(String.format("Genre with id=%s not found", genreId)));
        genre.setName(genreName);
        List<Book> books = bookRepository.findBookByGenresId(genreId);
        books.forEach(b -> b.getGenres().forEach(g -> g.setName(genreName)));
        books.forEach(b -> bookRepository.save(b));
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    public void delete(String genreId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(
                () -> new DataNotFoundException(String.format("Genre with id=%s not found", genreId)));
        List<Book> books = bookRepository.findBookByGenresId(genreId);
        if (books.size() > 0) {
            throw new DataNotFoundException("Operation is not acceptable. There are the books of this genre");
        }
        genreRepository.delete(genre);
    }
}
