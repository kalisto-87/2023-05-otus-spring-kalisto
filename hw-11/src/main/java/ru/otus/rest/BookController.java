package ru.otus.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.service.BookService;

@RestController
@RequestMapping
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("api/book")
    public Flux<BookDto> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("api/book/{id}")
    public Mono<BookDto> getBook(@PathVariable("id") String bookdId) {
        return bookService.findById(bookdId);
    }

    @PostMapping("api/book")
    public Mono<BookDto> createBook(@RequestBody BookDto bookDto) {
        return bookService.insert(bookDto);
    }

    @PatchMapping("api/book")
    public Mono<BookDto> updateBook(@RequestBody BookDto bookDto) {
        return bookService.update(bookDto);
    }

    @DeleteMapping("api/book/{id}")
    public void deleteBook(@PathVariable("id") String id) {
        Mono<BookDto> book = bookService.findById(id);
        if (book != null) {
            bookService.delete(id).block();
        }
    }

}
