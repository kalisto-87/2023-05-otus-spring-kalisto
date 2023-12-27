package ru.otus.page;

import org.hibernate.annotations.Comments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;
import ru.otus.mapper.BookMapper;
import ru.otus.mapper.CommentMapper;
import ru.otus.rest.CommentController;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class BookPageController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final CommentService commentService;

    private final BookMapper bookMapper;

    private final CommentMapper commentMapper;

    public BookPageController(BookService bookService, BookMapper bookMapper, CommentService commentService, CommentMapper commentMapper, AuthorService authorService) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.authorService = authorService;
    }

    @GetMapping()
    public String mainPage() {
        return "index";
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

    @GetMapping("/author")
    public String getAuthors() {
        return "authors";
    }

    @GetMapping("addAuthor")
    public String addAuthor() {
        return "addAuthor";
    }

    @GetMapping(value = "/author", params = {"id"})
    public String editAuthor(@RequestParam long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "editAuthor";
    }

    @GetMapping("/genre")
    public String getGenres() {
        return "genres";
    }

}
