package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.domain.Author;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Репозиторий Author")
@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void checkGetAll() {
        List<Author> authors = authorRepository.findAll();
        assertEquals(4, authors.size());
    }

    @Test
    public void checkFindByName() {
        String s = "ja";
        List<Author> authors = authorRepository.findByNameContainingIgnoreCase(s);
        assertEquals(2, authors.size());
    }

    @Test
    public void checkFindId() {
        long authorId = 1L;
        Author author = authorRepository.findById(authorId).orElse(null);
        Author expectedAuthor = new Author(authorId, "Jack London");
        assertEquals(expectedAuthor, author);
    }

    @Test
    public void checkSaveAuthor() {
        Author author = new Author(0, "Balzac");
        author = authorRepository.save(author);
        Author expectedAuthor = authorRepository.findById(author.getId()).orElse(null);
        assertEquals(expectedAuthor, author);
    }

    @Test
    public void checkDeleteAuthor() {
        long authorId = 4L;
        Author author = authorRepository.findById(authorId).orElse(null);
        assertNotNull(author);
        authorRepository.deleteById(authorId);
        author = authorRepository.findById(authorId).orElse(null);
        assertNull(author);
    }
}
