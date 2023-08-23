package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.converter.BookConverter;
import ru.otus.repository.BookRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Сервис Book")
@SpringBootTest
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookConverter bookConverter;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("Найти книгу по автору")
    public void checkFindByAuthorId() {
        long authorId = 1L;
        String exprectedValue = "List of the books belong to author 'Jack London':\n" +
                "ID=2; TITLE=The Star Rover; AUTHOR=Jack London; GENRE=science fiction\n" +
                "ID=3; TITLE=Martin Iden; AUTHOR=Jack London; GENRE=novel";
        assertEquals(exprectedValue, bookService.findByAuthorId(authorId));
    }

    @Test
    @DisplayName("Обновить книгу ")
    public void checkUpdateBookbyId() {
        long bookId = 1L;
        String bookName = "New book";
        assertEquals("Сhanges have been successfully implemented", bookService.update(bookId, bookName));
    }

    @Test
    @DisplayName("Удалить книгу из библиотеки")
    public void checkDeleteBookbyId() {
        long bookId = 4L;
        assertEquals("Сhanges have been successfully implemented", bookService.delete(bookId));
    }
}
