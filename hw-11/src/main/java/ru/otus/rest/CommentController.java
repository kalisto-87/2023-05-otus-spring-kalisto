package ru.otus.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;
import ru.otus.mapper.CommentMapper;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;


@RestController
@RequestMapping
public class CommentController {

    private final CommentService commentService;

    private final BookService bookService;

    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, BookService bookService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.bookService = bookService;
        this.commentMapper = commentMapper;
    }

    @GetMapping("api/book/{id}/comment")
    public Flux<CommentDto> getComments(@PathVariable("id") String bookId) {
        Mono<BookDto> mbookDto = bookService.findById(bookId);
        BookDto bookDto = mbookDto.block();
        return commentService.findCommentsByBook(bookDto);
    }

    @PostMapping("api/book/{id}/comment")
    public Mono<CommentDto> addComment(@PathVariable("id") String bookId, @RequestBody CommentDto commentDto) {
        return commentService.insert(commentDto, bookId);
    }

    @DeleteMapping("api/book/{id}/comment")
    public Mono<Void> deleteCommentByBook(@PathVariable("id") String bookId) {
        return commentService.deleteByBookId(bookId);
    }
}
