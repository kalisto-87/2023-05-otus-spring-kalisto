package ru.otus.page;

import org.hibernate.annotations.Comments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;
import ru.otus.mapper.BookMapper;
import ru.otus.mapper.CommentMapper;
import ru.otus.rest.CommentController;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class BookPageController {

    private final BookService bookService;

    private final CommentService commentService;

    private final BookMapper bookMapper;

    private final CommentMapper commentMapper;

    public BookPageController(BookService bookService, BookMapper bookMapper, CommentService commentService, CommentMapper commentMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @GetMapping("/library")
    public String getLibrary() {
        return "library";
    }

    @GetMapping("/book/{id}")
    public String getBookById(@PathVariable("id") long bookId) {
        return "bookInfo";
    }

    @GetMapping("add")
    public String getPageForBookProperties() {
        return "newBook";
    }

}
