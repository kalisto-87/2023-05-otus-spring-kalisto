package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.converter.AuthorConverter;
import ru.otus.domain.Author;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Сервис Author")
@SpringBootTest
public class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorConverter authorConverter;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("Получить список авторов по имени")
    public void checkFindByName() {
        String authorName = "Jack London";
        List<Author> authors = List.of(new Author(1, authorName));
        String expectedValue =
                String.format("List of the authors containing '%s':\n%s", authorName,
                        authors.stream().map(author -> authorConverter.convert(author)).
                                collect(Collectors.joining("\n")));
        assertEquals(expectedValue, authorService.findByName(authorName));
    }

    @Test
    @DisplayName("Вставка новой записи")
    public void checkInsertAuthor() {
        String authorName = "New Author";
        Author author = new Author(0, authorName);
        String exprectedValue = "Author has been created with id=5";
        assertEquals(exprectedValue, authorService.insert(authorName));
    }

    @Test
    @DisplayName("Обновить фамилию автора по идентификатору")
    public void checkUpdateAuthor() {
        long authorId = 2L;
        Author author = new Author(authorId, "New Author");
        String expectedValue = "Сhanges have been successfully implemented";
        assertEquals(expectedValue, authorService.update(author.getId(), author.getName()));
    }

    @Test
    @DisplayName("Обновить фамилию автора по идентификатору, которого нет в БД")
    public void checkUpdateNonExistentAuthor() {
        long authorId = 10L;
        Author author = new Author(authorId, "New Author");
        assertThrows(DataNotFoundException.class, () -> authorService.update(author.getId(), author.getName()));
    }

    @Test
    @DisplayName("Удалить автора по идентификатору")
    public void checkDeleteAuthor() {
        long authorId = 4L;
        String expectedValue = "Сhanges have been successfully implemented";
        assertEquals(expectedValue, authorService.delete(authorId));
    }

    @Test
    @DisplayName("Удалить автора по идентификатору, которого нет в БД")
    public void checkDeleteNonExistentAuthor() {
        long authorId = 5L;
        assertThrows(DataNotFoundException.class, () -> authorService.delete(authorId));
    }
}
