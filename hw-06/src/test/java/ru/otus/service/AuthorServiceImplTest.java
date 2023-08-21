package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.converter.AuthorConverter;
import ru.otus.domain.Author;
import ru.otus.repository.AuthorRepositoryJpa;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("Сервис Author")
@SpringBootTest
public class AuthorServiceImplTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorConverter authorConverter;

    @MockBean
    private AuthorRepositoryJpa authorRepositoryJpa;

    @Test
    public void checkInsertAuthor() {
        long authorId = 1L;
        Author author = new Author(0, "New Author");
        Author exprectedAuthor = new Author(authorId, "New Author");
        when(authorRepositoryJpa.insert(any())).thenReturn(exprectedAuthor);
        String s = authorService.insert(author.getName());
        String exprectedValue = String.format(
                "New record has been created in the library %s",
                authorConverter.convert(exprectedAuthor));
        assertEquals(exprectedValue, s);
    }

    @Test
    public void checkUpdateAuthor() {
        long authorId = 1L;
        Author author = new Author(authorId, "New Author");
        when(authorRepositoryJpa.update(any())).thenReturn(true);
        String expectedValue = String.format("The record with id=%s has been updated", author.getId());
        assertEquals(expectedValue, authorService.update(author.getId(), author.getName()));
    }

    @Test
    public void checkDeleteAuthor() {
        long authorId = 1L;
        when(authorRepositoryJpa.delete(authorId)).thenReturn(true);
        String expectedValue = String.format("The record with id=%s has been deleted from the library",
                authorId);
        assertEquals(expectedValue, authorService.delete(authorId));
    }

    @Test
    public void checkFindByName() {
        String authorName = "Jack London";
        List<Author> authors = List.of(new Author(1, authorName));
        when(authorRepositoryJpa.findByName(authorName)).thenReturn(authors);
        String expectedValue =
                String.format("List of the authors containing '%s':\n %s", authorName, authors.stream().map(author -> authorConverter.convert(author)).
                        collect(Collectors.joining("\n")));
        assertEquals(expectedValue, authorService.findByName(authorName));
    }
}
