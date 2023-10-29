package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.BookRepository;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    @GetMapping("/book/list")
    public String bookList(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "/book/list";
    }

    @GetMapping("/book")
    public String bookEdit(@RequestParam long id, Model model) {
        Book book = bookService.findById(id).
                orElseThrow(() -> new DataNotFoundException(String.format("The book with id = %s not found", id)));
        model.addAttribute("book", book);
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "book/edit";
    }

    @PostMapping("/edit")
    public String saveBook(Book book) {

        return "redirect:/book/list";
    }
}
