package ru.otus.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.domain.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Репозиторий Genre")
@DataJpaTest
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
        long genreId = 1L;
        Genre genre = genreRepository.findById(genreId).orElse(null);
        Genre expectedGenre = new Genre(genreId, "novel");
        assertEquals(expectedGenre, genre);
    }

    @Test
    public void checkSaveGenre() {
        Genre genre = new Genre(0, "Balzac");
        genre = genreRepository.save(genre);
        Genre expectedGenre = genreRepository.findById(genre.getId()).orElse(null);
        assertEquals(expectedGenre, genre);
    }

    @Test
    public void checkDeleteGenre() {
        long genreId = 3L;
        Genre genre = genreRepository.findById(genreId).orElse(null);
        assertNotNull(genre);
        genreRepository.deleteById(genreId);
        genre = genreRepository.findById(genreId).orElse(null);
        assertNull(genre);
    }
}
