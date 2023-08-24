package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий для работы с сущностью Genre")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
public class GenreRepositoryJpaTest {
    @Autowired
    private GenreRepositoryJpa jpa;

    @Autowired
    private TestEntityManager em;

    @Test
    public void checkInsertAuthor() {
        Genre newGenre = new Genre(0,"New genre");
        Genre insertedGenre = jpa.insert(newGenre);
        Genre getGenre = jpa.findById(insertedGenre.getId()).orElse(null);
        assertEquals(newGenre, getGenre);
    }

    @Test
    public void checkUpdateAuthor() {
        long genreId = 1L;
        String newName = "New unknown genre";
        Genre getGenre = jpa.findById(genreId).orElse(null);
        getGenre.setName(newName);
        jpa.update(getGenre);
        Genre updatedGenre = jpa.findById(genreId).orElse(null);
        assertEquals(newName, updatedGenre.getName());
    }

    @Test
    public void checkDeleteAuthor() {
        long genreId = 3L;
        Genre expectedGenre = jpa.findById(genreId).orElse(null);
        assertNotNull(expectedGenre);
        jpa.delete(expectedGenre);
        expectedGenre = jpa.findById(genreId).orElse(null);
        assertNull(expectedGenre);
    }

    @Test
    public void checkGetAll() {
        List<Genre> genreIdList = jpa.findAll();
        assertEquals(3, genreIdList.size());
    }

    @Test
    public void checkFindByName() {
        String name = "sci";
        List<Genre> genreList = jpa.findByName(name);
        assertEquals(1, genreList.size());
    }

    @Test
    public void checkFindById() {
        Long id = 1L;
        Genre genre = jpa.findById(1L).orElse(null);
        assertNotNull(genre);
    }
}
