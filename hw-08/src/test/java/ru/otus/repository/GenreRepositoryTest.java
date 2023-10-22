package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;
import ru.otus.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий Genre")
@DataMongoTest
public class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void checkGetAll() {
        List<Genre> genres = genreRepository.findAll();
        assertEquals(3, genres.size());
    }

    @Test
    public void checkFindByName() {
        String s = "sci";
        List<Genre> genres = genreRepository.findByNameContainingIgnoreCase(s);
        assertEquals(1, genres.size());
    }

    @Test
    public void checkFindId() {
        List<Genre> findGenre = genreRepository.findByNameContainingIgnoreCase("novel");
        String genreId = findGenre.get(0).getId();
        Genre genre = genreRepository.findById(genreId).orElse(null);
        Genre expectedGenre = new Genre(genreId, "novel");
        assertEquals(expectedGenre, genre);
    }

    @Test
    public void checkSaveGenre() {
        Genre genre = new Genre("New genre");
        genre = genreRepository.save(genre);
        Genre expectedGenre = genreRepository.findById(genre.getId()).orElse(null);
        assertEquals(expectedGenre, genre);
    }

    @Test
    public void checkDeleteGenre() {
        List<Genre> findGenre = genreRepository.findByNameContainingIgnoreCase("novel");
        String genreId = findGenre.get(0).getId();
        Genre genre = genreRepository.findById(genreId).orElse(null);
        assertNotNull(genre);
        genreRepository.deleteById(genreId);
        genre = genreRepository.findById(genreId).orElse(null);
        assertNull(genre);
    }
}
