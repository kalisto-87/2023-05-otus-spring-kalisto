package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.converter.GenreConverter;
import ru.otus.domain.Genre;
import ru.otus.repository.GenreRepositoryJpa;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Сервис Genre")
@SpringBootTest
public class GenreServiceImplTest {

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreConverter genreConverter;

    @MockBean
    private GenreRepositoryJpa genreRepositoryJpa;

    @Test
    public void checkInsertAuthor() {
        long genreId = 1L;
        Genre genre = new Genre(0, "New Genre");
        Genre exprectedGenre = new Genre(genreId, "New Genre");
        when(genreRepositoryJpa.insert(any())).thenReturn(exprectedGenre);
        String s = genreService.insert(genre.getName());
        String exprectedValue = String.format(
                "New record has been created in the library %s",
                genreConverter.convert(exprectedGenre));
        assertEquals(exprectedValue, s);
    }

    @Test
    public void checkUpdateAuthor() {
        long genreId = 1L;
        Genre genre = new Genre(genreId, "New Genre");
        when(genreRepositoryJpa.update(any())).thenReturn(true);
        String expectedValue = String.format("The record with id=%s has been updated", genre.getId());
        assertEquals(expectedValue, genreService.update(genre.getId(), genre.getName()));
    }

    @Test
    public void checkDeleteAuthor() {
        long genreId = 1L;
        when(genreRepositoryJpa.delete(genreId)).thenReturn(true);
        String expectedValue = String.format("The record with id=%s has been deleted from the library",
                genreId);
        assertEquals(expectedValue, genreService.delete(genreId));
    }

    @Test
    public void checkFindByName() {
        String genreName = "Jack London";
        List<Genre> genres = List.of(new Genre(1, genreName));
        when(genreRepositoryJpa.findByName(genreName)).thenReturn(genres);
        String expectedValue =
                String.format("List of the genres containing '%s':\n %s", genreName, genres.stream().map(genre -> genreConverter.convert(genre)).
                        collect(Collectors.joining("\n")));
        assertEquals(expectedValue, genreService.findByName(genreName));
    }
}
