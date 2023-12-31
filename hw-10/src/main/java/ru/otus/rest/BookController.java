package ru.otus.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dto.BookDto;
import ru.otus.service.BookService;

import java.util.List;

@RestController
@RequestMapping
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("api/book")
    public List<BookDto> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("api/book/{id}")
    public BookDto getBook(@PathVariable("id") long bookdId) {
        return bookService.findById(bookdId);
    }

    @PostMapping("api/book")
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookService.insert(bookDto);
    }

    @PatchMapping("api/book")
    public BookDto updateBook(@RequestBody BookDto bookDto) {
        return bookService.update(bookDto);
    }

    @DeleteMapping("api/book/{id}")
    public void deleteBook(@PathVariable("id") long id) {
        bookService.delete(id);
    }

}
