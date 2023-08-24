package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.converter.AuthorConverter;
import ru.otus.domain.Author;
import ru.otus.exception.DataNotFoundException;
import ru.otus.service.transfer.AuthorConvertToUI;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@DisplayName("Сервис Author")
@SpringBootTest
public class AuthorServiceImplTest {

    @Autowired
    private AuthorConvertToUI authorConvertToUI;

    @Autowired
    private AuthorConverter authorConverter;

    @MockBean
    private AuthorService authorService;

    @Test
    @DisplayName("Вставка новой записи")
    public void checkInsertAuthor() {
        long authorId = 1L;
        Author author = new Author(0, "New Author");
        Author exprectedAuthor = new Author(authorId, "New Author");
        when(authorService.insert(any())).thenReturn(exprectedAuthor);
        String s = authorConvertToUI.insert(author.getName());
        String exprectedValue = String.format(
                "New record has been created in the library %s",
                authorConverter.convert(exprectedAuthor));
        assertEquals(exprectedValue, s);
    }

    @Test
    @DisplayName("Обновить фамилию автора по идентификатору")
    public void checkUpdateAuthor() {
        long authorId = 1L;
        String newName = "New author";
        Author author = new Author(authorId, newName);
        when(authorService.update(authorId, author.getName())).thenReturn(author);
        String expectedValue = String.format("The record with id=%s has been updated", author.getId());
        assertEquals(expectedValue, authorConvertToUI.update(author.getId(), author.getName()));
    }

    @Test
    @DisplayName("Обновить фамилию автора по идентификатору, которого нет в БД")
    public void checkUpdateNonExistentAuthor() {
        long authorId = 5L;
        String newName = "New author";
        Author author = new Author(authorId, newName);
        when(authorService.update(authorId, newName)).thenThrow(new DataNotFoundException("Author not found"));
        assertEquals("Author not found", authorConvertToUI.update(authorId, newName));
    }

    @Test
    @DisplayName("Удалить автора по идентификатору")
    public void checkDeleteAuthor() {
        long authorId = 1L;
        doNothing().when(authorService).delete(authorId);
        String expectedValue = String.format("The record with id=%s has been deleted from the library", authorId);
        assertEquals(expectedValue, authorConvertToUI.delete(authorId));
    }

    @Test
    @DisplayName("Удалить автора по идентификатору, которого нет в БД")
    public void checkDeleteNonExistentAuthor() {
        long authorId = 1L;
        doThrow(new DataNotFoundException("Author not found")).when(authorService).delete(authorId);
        assertEquals("Author not found", authorConvertToUI.delete(authorId));
    }

    @Test
    @DisplayName("Получить список авторов по имени")
    public void checkFindByName() {
        String authorName = "Jack London";
        List<Author> authors = List.of(new Author(1, authorName));
        when(authorService.findByName(authorName)).thenReturn(authors);
        String expectedValue =
                String.format("List of the authors containing '%s':\n%s", authorName, authors.stream().map(author -> authorConverter.convert(author)).
                        collect(Collectors.joining("\n")));
        assertEquals(expectedValue, authorConvertToUI.findByName(authorName));
    }
}
