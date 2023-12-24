package ru.otus.rest;

import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Book;
import ru.otus.dto.BookDto;
import ru.otus.mapper.BookMapper;
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
}
