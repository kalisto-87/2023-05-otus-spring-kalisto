package ru.otus.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.dto.BookDto;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;
import ru.otus.service.GenreService;

@Controller
@RequestMapping("/")
public class PageController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    private final CommentService commentService;

    public PageController(BookService bookService, CommentService commentService,
                          AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.commentService = commentService;
        this.authorService = authorService;
        this.genreService = genreService;
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

    @GetMapping("addBook")
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

    @GetMapping("addGenre")
    public String addGenre() {
        return "addGenre";
    }

    @GetMapping(value = "/genre", params = {"id"})
    public String editGenre(@RequestParam long id, Model model) {
        model.addAttribute("genre", genreService.findById(id));
        return "editGenre";
    }


    @GetMapping(value = "/addcomment", params = {"bookId"})
    public String addComment(@RequestParam("bookId") long bookId, Model model) {
        BookDto bookDto = bookService.findById(bookId);
        model.addAttribute("book", bookDto);
        return "addComment";
    }

}
