package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.converter.GenreConverter;
import ru.otus.domain.Genre;
import ru.otus.exception.DataNotFoundException;
import ru.otus.service.transfer.GenreConvertToUI;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@DisplayName("Сервис Genre")
@SpringBootTest
public class GenreServiceImplTest {

    @Autowired
    private GenreConvertToUI genreConvertToUI;

    @Autowired
    private GenreConverter genreConverter;

    @MockBean
    private GenreService genreService;

    @Test
    @DisplayName("Вставка новой записи")
    public void checkInsertGenre() {
        long genreId = 1L;
        String newGenre = "New Genre";
        Genre exprectedGenre = new Genre(genreId, newGenre);
        when(genreService.insert(any())).thenReturn(exprectedGenre);
        String s = genreConvertToUI.insert(newGenre);
        String exprectedValue = String.format(
                "New record has been created in the library %s",
                genreConverter.convert(exprectedGenre));
        assertEquals(exprectedValue, s);
    }

    @Test
    @DisplayName("Обновить название жанра по идентификатору")
    public void checkUpdateGenre() {
        long genreId = 1L;
        String newGenre = "New Genre";
        Genre genre = new Genre(genreId, newGenre);
        when(genreService.update(genreId, newGenre)).thenReturn(genre);
        String expectedValue = String.format("The record with id=%s has been updated", genre.getId());
        assertEquals(expectedValue, genreConvertToUI.update(genre.getId(), genre.getName()));
    }

    @Test
    @DisplayName("Обновить название жанра по идентификатору, которого нет в БД")
    public void checkUpdateNonExistentGenre() {
        long genreId = 5L;
        String newGenre = "New Genre";
        when(genreService.update(genreId, newGenre)).thenThrow(new DataNotFoundException("Genre not found"));
        assertEquals("Genre not found", genreConvertToUI.update(genreId, newGenre));
    }

    @Test
    @DisplayName("Удалить автора по идентификатору")
    public void checkDeleteGenre() {
        long genreId = 1L;
        doNothing().when(genreService).delete(genreId);
        String expectedValue = String.format("The record with id=%s has been deleted from the library", genreId);
        assertEquals(expectedValue, genreConvertToUI.delete(genreId));
    }

    @Test
    @DisplayName("Удалить автора по идентификатору, которого нет в БД")
    public void checkDeleteNonExistentGenre() {
        long genreId = 5L;
        doThrow(new DataNotFoundException("Genre not found")).when(genreService).delete(genreId);
        assertEquals("Genre not found", genreConvertToUI.delete(genreId));
    }

    @Test
    @DisplayName("Поиск жанров по названию")
    public void checkFindByName() {
        String genreName = "Science";
        List<Genre> genres = List.of(new Genre(1, genreName));
        when(genreService.findByName(genreName)).thenReturn(genres);
        String expectedValue =
                String.format("List of the genres containing '%s':\n%s", genreName, genres.stream().map(genre -> genreConverter.convert(genre)).
                        collect(Collectors.joining("\n")));
        assertEquals(expectedValue, genreConvertToUI.findByName(genreName));
    }
}
