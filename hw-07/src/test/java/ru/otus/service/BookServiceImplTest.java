package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.converter.BookConverter;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.service.transform.BookConvertToUI;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@DisplayName("Сервис Book")
@SpringBootTest
public class BookServiceImplTest {

    @Autowired
    private BookConvertToUI bookConvertToUI;

    @Autowired
    private BookConverter bookConverter;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("Найти книгу по автору")
    public void checkFindByAuthorId() {
        long authorId = 1L;
        Author author = new Author(authorId, "Jack London");
        String exprectedValue = "List of the books belonging to author 'Jack London':\n" +
                "ID=2; TITLE=The Star Rover; AUTHOR=Jack London; GENRE=science fiction\n" +
                "ID=3; TITLE=Martin Iden; AUTHOR=Jack London; GENRE=novel";
        when(bookService.findByAuthorId(author)).thenReturn(
                List.of(new Book(2, "The Star Rover",
                                List.of(new Author(1L, "Jack London")),
                                List.of(new Genre(2L, "science fiction"))),
                        new Book(3, "Martin Iden",
                                List.of(new Author(1L, "Jack London")),
                                List.of(new Genre(1L, "novel")))));
        assertEquals(exprectedValue, bookConvertToUI.findByAuthorId(authorId));
    }

    @Test
    @DisplayName("Обновить книгу ")
    public void checkUpdateBookbyId() {
        long bookId = 1L;
        String bookName = "New book";
        when(bookService.update(bookId, bookName)).thenReturn(new Book(bookId, bookName, null, null));
        assertEquals(String.format("The record with id=%s has been updated", bookId), bookConvertToUI.update(bookId, bookName));
    }

    @Test
    @DisplayName("Удалить книгу из библиотеки")
    public void checkDeleteBookbyId() {
        long bookId = 4L;
        doNothing().when(bookService).delete(bookId);
        assertEquals(String.format("The record with id=%s has been deleted from the library", bookId),
                bookConvertToUI.delete(bookId));
    }
}
