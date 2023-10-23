package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Book;
import ru.otus.repository.BookRepository;
import ru.otus.service.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book/list")
    public String bookList(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "/book/list";
    }
}
