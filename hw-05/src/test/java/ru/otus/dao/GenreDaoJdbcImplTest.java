package ru.otus.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@JdbcTest
@Import(GenreDaoJdbcImpl.class)
@DisplayName("Проверка работы методов сущности Genre")
public class GenreDaoJdbcImplTest {

    @Autowired
    private GenreDao dao;

    @Test
    public void checkCountOfGenres() {
        assertEquals(3, dao.getAll().size());
    }

    @Test
    public void checkFindByName() {
        String substr = "ic";
        assertEquals(2, dao.findByName(substr).size());
    }

    @Test
    public void checkFindById() {
        long genreId = 1L;
        Genre genre = new Genre(genreId, "novel");
        assertEquals(genre, dao.findById(genreId).orElseThrow());
    }

    @Test
    public void checkInsertGenre() {
        Genre newGenre = dao.insertGenre("New unknown genre");
        assertEquals(4, dao.getAll().size());
        assertNotNull(dao.findById(newGenre.getId()));
        assertEquals(newGenre, dao.findById(newGenre.getId()).orElse(null));
    }

    @Test
    public void checkUpdateGenre() {
        long genreId = 1L;
        Genre genre = dao.findById(genreId).orElse(null);
        Genre updateGenre = new Genre(genre.getId(), "Poem");
        dao.updateGenre(genreId,updateGenre.getName());
        assertEquals(updateGenre, dao.findById(genreId).orElse(null));
    }

    @Test
    public void checkDeleteGenre() {
        long genreId = 3L;
        int cntBefore = dao.getAll().size();
        dao.deleteGenre(genreId);
        int cntAfter = dao.getAll().size();
        assertEquals(cntBefore - 1, cntAfter);
    }

}
