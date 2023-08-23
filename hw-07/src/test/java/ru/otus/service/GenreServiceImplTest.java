package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.converter.GenreConverter;
import ru.otus.domain.Genre;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Сервис Genre")
@SpringBootTest
public class GenreServiceImplTest {

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreConverter genreConverter;

    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("Вставка новой записи")
    public void checkInsertGenre() {
        Genre genre = new Genre(0, "New Genre");
        String s = genreService.insert(genre.getName());
        String exprectedValue = "Genre has been created with id=4";
        assertEquals(exprectedValue, s);
    }

    @Test
    @DisplayName("Обновить название жанра по идентификатору")
    public void checkUpdateGenre() {
        long genreId = 2L;
        Genre genre = new Genre(genreId, "New Genre");
        String expectedValue = "Сhanges have been successfully implemented";
        assertEquals(expectedValue, genreService.update(genre.getId(), genre.getName()));
    }

    @Test
    @DisplayName("Обновить название жанра по идентификатору, которого нет в БД")
    public void checkUpdateNonExistentGenre() {
        long genreId = 10L;
        Genre genre = new Genre(genreId, "New Genre");
        assertThrows(DataNotFoundException.class, () -> genreService.update(genre.getId(), genre.getName()));
    }

    @Test
    @DisplayName("Удалить автора по идентификатору")
    public void checkDeleteGenre() {
        long genreId = 3L;
        String expectedValue = "Сhanges have been successfully implemented";
        assertEquals(expectedValue, genreService.delete(genreId));
    }

    @Test
    @DisplayName("Удалить автора по идентификатору, которого нет в БД")
    public void checkDeleteNonExistentGenre() {
        long genreId = 10L;
        assertThrows(DataNotFoundException.class, () -> genreService.delete(genreId));
    }

    @Test
    @DisplayName("Поиск жанров по названию")
    public void checkFindByName() {
        String genreName = "science fiction";
        List<Genre> genres = List.of(new Genre(2, genreName));
        String expectedValue =
                String.format("List of the genres containing '%s':\n%s", genreName, genres.stream().map(genre -> genreConverter.convert(genre)).
                        collect(Collectors.joining("\n")));
        assertEquals(expectedValue, genreService.findByName(genreName));
    }
}
