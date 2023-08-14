package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.Author;
import ru.otus.service.AuthorService;
import ru.otus.service.AuthorServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = AuthorServiceImpl.class)
@DisplayName("Проверка работы методов сервиса Author")
public class AuthorServiceImplTest {

    @MockBean
    private AuthorDao dao;

    @Autowired
    private AuthorService authorService;

    @Test
    public void checkCountOfAuthors() {
        List<Author> expectedAuthors = List.of(new Author(1L, "Jack London"));
        when(dao.getAll()).thenReturn(expectedAuthors);
        Assertions.assertEquals(expectedAuthors, dao.getAll());
    }

    @Test
    public void checkFindById() {
        long authorId = 1L;
        Author expectedAuthor = new Author(authorId, "Jack London");
        when(dao.findById(authorId)).thenReturn(Optional.of(expectedAuthor));
        Assertions.assertEquals(expectedAuthor, authorService.findById(authorId));
    }

    @Test
    public void checkInsertAuthor() {
        long authorId = 1L;
        Author expectedAuthor = new Author(authorId, "Jack London");
        when(dao.insertAuthor(expectedAuthor.getName())).thenReturn(expectedAuthor);
        Assertions.assertEquals(expectedAuthor, authorService.insert(expectedAuthor.getName()));
    }

    @Test
    public void checkUpdateAuthor() {
        long authorId = 1L;
        Author expectedAuthor = new Author(authorId, "Jack London");
        when(dao.updateAuthor(expectedAuthor.getId(), expectedAuthor.getName())).thenReturn(true);
        Assertions.assertTrue(authorService.update(expectedAuthor.getId(), expectedAuthor.getName()));
    }

    @Test
    public void deleteUpdateAuthor() {
        long authorId = 1L;
        when(dao.deleteAuthor(authorId)).thenReturn(true);
        Assertions.assertTrue(authorService.delete(authorId));
    }
}
