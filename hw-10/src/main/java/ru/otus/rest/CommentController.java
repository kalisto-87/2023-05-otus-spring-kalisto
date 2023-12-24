package ru.otus.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;

import java.util.List;

@RestController
@RequestMapping
public class CommentController {

    private final CommentService commentService;

    private final BookService bookService;

    public CommentController(CommentService commentService, BookService bookService) {
        this.commentService = commentService;
        this.bookService = bookService;
    }

    @GetMapping("api/book/{id}/comment")
    public List<CommentDto> getComments(@PathVariable("id") long bookdId) {
        BookDto bookDto = bookService.findById(bookdId);
        return commentService.findCommentsByBook(bookDto);
    }
}
