package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;
import ru.otus.service.AuthorService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void checkGetAll() {
        List<Author> authorList = jpa.findAll();
        assertEquals(4, authorList.size());
    }

}
