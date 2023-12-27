package ru.otus.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.dto.BookDto;
import ru.otus.mapper.BookMapper;
import ru.otus.mapper.CommentMapper;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;

@Controller
@RequestMapping("/")
public class BookPageController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final CommentService commentService;

    private final BookMapper bookMapper;

    private final CommentMapper commentMapper;

    public BookPageController(BookService bookService, BookMapper bookMapper, CommentService commentService,
                              CommentMapper commentMapper, AuthorService authorService) {
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

    @GetMapping("/book")
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

    @GetMapping(value = "/addcomment", params = {"bookId"})
    public String addComment(@RequestParam("bookId") long bookId, Model model) {
        BookDto bookDto = bookService.findById(bookId);
        model.addAttribute("book", bookDto);
        return "addComment";
    }

}
