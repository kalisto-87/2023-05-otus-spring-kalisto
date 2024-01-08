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
    public List<CommentDto> getComments(@PathVariable("id") long bookId) {
        BookDto bookDto = bookService.findById(bookId);
        return commentService.findCommentsByBook(bookDto);
    }

    @PostMapping("api/book/{id}/comment")
    public CommentDto addComment(@PathVariable("id") long bookId, @RequestBody CommentDto commentDto) {
        BookDto bookDto = bookService.findById(bookId);
        return commentService.insert(commentDto, bookDto.getId());
    }

    @PatchMapping("api/book/{id}/comment")
    public CommentDto editComment(@PathVariable("id") long bookId, @RequestBody CommentDto commentDto) {
        BookDto bookDto = bookService.findById(bookId);
        return commentService.update(commentDto);
    }

    @DeleteMapping("api/book/{id}/comment")
    public void deleteCommentByBook(@PathVariable("id") long bookId) {
        BookDto bookDto = bookService.findById(bookId);
        commentService.deleteByBook(bookDto);
    }
}
