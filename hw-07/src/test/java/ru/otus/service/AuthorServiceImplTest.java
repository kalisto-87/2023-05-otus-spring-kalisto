package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.converter.AuthorConverter;
import ru.otus.domain.Author;
import ru.otus.exception.DataNotFoundException;
import ru.otus.service.transform.AuthorConvertToUI;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    @DisplayName("Получить список авторов по имени")
    public void checkFindByName() {
        String authorName = "Jack London";
        List<Author> authors = List.of(new Author(1, authorName));
        when(authorService.findByName(any())).thenReturn(authors);
        String expectedValue =
                String.format("List of the authors containing '%s':\n%s", authorName,
                        authors.stream().map(author -> authorConverter.convert(author)).
                                collect(Collectors.joining("\n")));
        assertEquals(expectedValue, authorConvertToUI.findByName(authorName));
    }

    @Test
    @DisplayName("Вставка новой записи")
    public void checkInsertAuthor() {
        String authorName = "New Author";
        Author author = new Author(0, authorName);
        Author newAuthor = new Author(5, authorName);
        String expectedValue = String.format("New record has been created in the library %s",
                authorConverter.convert(newAuthor));
        when(authorService.insert(author)).thenReturn(newAuthor);
        assertEquals(expectedValue, authorConvertToUI.insert(authorName));
    }

    @Test
    @DisplayName("Обновить фамилию автора по идентификатору")
    public void checkUpdateAuthor() {
        long authorId = 2L;
        Author author = new Author(authorId, "New Author");
        String expectedValue = String.format("The record with id=%s has been updated", authorId);
        when(authorService.update(authorId, author.getName())).thenReturn(author);
        assertEquals(expectedValue, authorConvertToUI.update(author.getId(), author.getName()));
    }

    @Test
    @DisplayName("Обновить фамилию автора по идентификатору, которого нет в БД")
    public void checkUpdateNonExistentAuthor() {
        long authorId = 10L;
        Author author = new Author(authorId, "New Author");
        when(authorService.update(authorId, author.getName())).
                thenThrow(new DataNotFoundException(String.format("Author with id=%s not found", author.getId())));
        String expectdValue = String.format("Author with id=%s not found", authorId);
        assertEquals(expectdValue, authorConvertToUI.update(author.getId(), author.getName()));
    }

    @Test
    @DisplayName("Удалить автора по идентификатору")
    public void checkDeleteAuthor() {
        long authorId = 4L;
        String expectedValue = String.format("The record with id=%s has been deleted from the library", authorId);
        doNothing().when(authorService).delete(authorId);
        assertEquals(expectedValue, authorConvertToUI.delete(authorId));
    }

    @Test
    @DisplayName("Удалить автора по идентификатору, которого нет в БД")
    public void checkDeleteNonExistentAuthor() {
        long authorId = 5L;
        String expectedValue = String.format("Author with id =%s not found", authorId);
        doThrow(new DataNotFoundException(String.format("Author with id =%s not found", authorId))).
                when(authorService).delete(authorId);
        assertEquals(expectedValue, authorConvertToUI.delete(authorId));
    }
}
