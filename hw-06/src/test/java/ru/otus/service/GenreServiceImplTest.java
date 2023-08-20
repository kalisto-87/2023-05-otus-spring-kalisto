package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.context.annotation.Import;
import ru.otus.domain.Genre;
import ru.otus.repository.GenreRepositoryJpa;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Репозиторий для работы с сущностью Genre")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
public class GenreServiceImplTest {

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
        assertEquals(newGenre.getName(), getGenre.getName());
    }

    @Test
    public void checkGetAll() {
        List<Genre> genreList = jpa.findAll();
        assertEquals(3, genreList.size());
    }
}
