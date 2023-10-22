package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.domain.Author;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Репозиторий Author")
@DataMongoTest
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
        List<Author> findAuthor = authorRepository.findByNameContainingIgnoreCase("Jack London");
        String authorId = findAuthor.get(0).getId();
        Author author = authorRepository.findById(authorId).orElse(null);
        Author expectedAuthor = new Author(findAuthor.get(0).getId(), "Jack London");
        assertEquals(expectedAuthor, author);
    }

    @Test
    public void checkSaveAuthor() {
        Author author = new Author("Balzac");
        author = authorRepository.save(author);
        Author expectedAuthor = authorRepository.findById(author.getId()).orElse(null);
        assertEquals(expectedAuthor, author);
    }

    @Test
    public void checkDeleteAuthor() {
        List<Author> findAuthor = authorRepository.findByNameContainingIgnoreCase("Jack London");
        String authorId = findAuthor.get(0).getId();
        Author author = authorRepository.findById(authorId).orElse(null);
        assertNotNull(author);
        authorRepository.deleteById(authorId);
        author = authorRepository.findById(authorId).orElse(null);
        assertNull(author);
    }
}
