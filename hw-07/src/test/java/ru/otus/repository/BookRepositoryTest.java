package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий Book")
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void checkGetAll() {
        List<Book> books = bookRepository.findAll();
        assertEquals(4, books.size());
    }

    @Test
    public void checkFindByName() {
        String s = "star";
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(s);
        assertEquals(1, books.size());
    }

    @Test
    public void checkFindId() {
        long authorId = 1L;
        Book book = bookRepository.findById(authorId).orElse(null);
        assertNotNull(book);
    }

    @Test
    public void checkSaveBook() {
        Author author1 = new Author(0, "Ilf");
        author1 = authorRepository.save(author1);
        Author author2 = new Author(0, "Petrov");
        author2 = authorRepository.save(author2);
        Genre genre = new Genre(0, "Satirical comedy");
        genre = genreRepository.save(genre);
        List<Author> authors = List.of(author1, author2);
        List<Genre> genres = List.of(genre);
        Book book = new Book(0, "The twelve chairs", authors, genres);
        book = bookRepository.save(book);
        Book expectedBook = bookRepository.findByTitleContainingIgnoreCase(book.getTitle()).get(0);
        assertEquals(expectedBook.getId(), book.getId());
    }

    @Test
    public void checkDeleteAuthor() {
        long bookId = 4L;
        Book book = bookRepository.findById(bookId).orElse(null);
        assertNotNull(book);
        bookRepository.deleteById(bookId);
        book = bookRepository.findById(bookId).orElse(null);
        assertNull(book);
    }
}
