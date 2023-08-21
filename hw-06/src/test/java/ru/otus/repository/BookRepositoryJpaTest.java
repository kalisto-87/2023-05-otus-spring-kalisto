package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с сущностью Book")
@DataJpaTest
@Import({BookRepositoryJpa.class, AuthorRepositoryJpa.class, GenreRepositoryJpa.class})
public class BookRepositoryJpaTest {

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;

    @Autowired
    private AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;

    @Autowired
    private TestEntityManager em;

    @Test
    public void checkGetAll() {
        List<Book> books = bookRepositoryJpa.findAll();
        assertEquals(4, books.size());
    }

    @Test
    public void checkFindById() {
        String bookName = "bovary";
        List<Book> books = bookRepositoryJpa.findByName(bookName);
        assertEquals(1, books.size());
    }

    @Test
    public void checkCreateBook() {
        Author author1 = new Author(0, "Ilf");
        author1 = authorRepositoryJpa.insert(author1);
        Author author2 = new Author(0, "Petrov");
        author2 = authorRepositoryJpa.insert(author2);
        Genre genre = new Genre(0,"Satirical novel");
        genre = genreRepositoryJpa.insert(genre);
        Book book = new Book(0, "The twelve chairs",
                List.of(author1, author2), List.of(genre));
        Book insertedBook = bookRepositoryJpa.insert(book);
        Book expectedBook = bookRepositoryJpa.findByName(book.getTitle()).get(0);
        assertEquals(insertedBook, expectedBook);
    }

    @Test
    public void checkUpdateBook() {
        String newTitle = "Another title";
        long bookId = 1L;
        Book book = bookRepositoryJpa.findById(bookId).orElse(null);
        book.setTitle(newTitle);
        bookRepositoryJpa.update(book);
        book = bookRepositoryJpa.findById(bookId).orElse(null);
        assertNotNull(book);
        assertEquals(newTitle, book.getTitle());
    }

    @Test
    public void checkDeleteBook() {
        long bookId = 1L;
        Book book = bookRepositoryJpa.findById(bookId).orElse(null);
        assertNotNull(book);
        bookRepositoryJpa.delete(bookId);
        book = bookRepositoryJpa.findById(bookId).orElse(null);
        assertNull(book);
    }
}
