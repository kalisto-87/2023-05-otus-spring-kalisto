package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JdbcTest
@Import(AuthorDaoJdbcImpl.class)
@DisplayName("Проверка работы методов сущности Author")
public class AuthorDaoJdbcImplTest {

    @Autowired
    private AuthorDao dao;

    @Test
    public void checkCountOfAuthors() {
        assertEquals(4, dao.getAll().size());
    }

    @Test
    public void checkFindByName() {
        String substr = "l";
        assertEquals(3, dao.findByName(substr).size());
    }

    @Test
    public void checkFindById() {
        long authorId = 1L;
        Author author = new Author(authorId, "Jack London");
        assertEquals(author, dao.findById(authorId).orElseThrow());
    }

    @Test
    public void checkInsertAuthor() {
        Author newAuthor = dao.insertAuthor("Adorable author");
        assertEquals(5, dao.getAll().size());
        assertNotNull(dao.findById(newAuthor.getId()));
        assertEquals(newAuthor, dao.findById(newAuthor.getId()).orElse(null));
    }

    @Test
    public void checkUpdateAuthor() {
        long authorId = 1L;
        Author author = dao.findById(authorId).orElse(null);
        Author updateAuthor = new Author(author.getId(), "Fenimor Kuper");
        dao.updateAuthor(authorId,updateAuthor.getName());
        assertEquals(updateAuthor, dao.findById(authorId).orElse(null));
    }

    @Test
    public void checkDeleteAuthor() {
        long authorId = 4L;
        int cntBefore = dao.getAll().size();
        dao.deleteAuthor(authorId);
        int cntAfter = dao.getAll().size();
        assertEquals(cntBefore - 1, cntAfter);
    }

}
