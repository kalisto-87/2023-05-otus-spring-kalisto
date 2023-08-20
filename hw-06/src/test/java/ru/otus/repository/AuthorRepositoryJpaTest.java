package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с сущностью Author")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
public class AuthorRepositoryJpaTest {

    @Autowired
    private AuthorRepositoryJpa jpa;

    @Autowired
    private TestEntityManager em;

    @Test
    public void checkInsertAuthor() {
        Author newAuthor = new Author(0,"Jack London");
        Author insertedAuthor = jpa.insert(newAuthor);
        Author getAuthor = jpa.findById(insertedAuthor.getId()).orElse(null);
        assertEquals(newAuthor, getAuthor);
    }

    @Test
    public void checkUpdateAuthor() {
        long authorId = 1L;
        String newName = "New unknown author";
        Author getAuthor = jpa.findById(authorId).orElse(null);
        getAuthor.setName(newName);
        jpa.update(getAuthor);
        Author updatedAuthor = jpa.findById(authorId).orElse(null);
        assertEquals(newName, updatedAuthor.getName());
    }

    @Test
    public void checkDeleteAuthor() {
        long authorId = 4L;
        Author expectedAuthor = jpa.findById(authorId).orElse(null);
        assertNotNull(expectedAuthor);
        jpa.delete(authorId);
        expectedAuthor = jpa.findById(authorId).orElse(null);
        assertNull(expectedAuthor);
    }

    @Test
    public void checkGetAll() {
        List<Author> authorList = jpa.findAll();
        assertEquals(4, authorList.size());
    }

    @Test
    public void checkFindByName() {
        String name = "Jack";
        List<Author> authorList = jpa.findByName(name);
        assertEquals(1, authorList.size());
    }

    @Test
    public void checkFindById() {
        Long id = 1L;
        Author author = jpa.findById(1L).orElse(null);
        assertNotNull(author);
    }

}
