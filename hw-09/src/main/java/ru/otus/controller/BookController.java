package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.dto.AuthorDto;
import ru.otus.dto.BookDto;
import ru.otus.dto.GenreDto;
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
        List<BookDto> bookDtoList = bookService.findAll();
        model.addAttribute("books", bookDtoList);
        return "/book/list";
    }

    @GetMapping("/book")
    public String bookEdit(@RequestParam long id, Model model) {
        BookDto bookDto = bookService.findById(id);
        model.addAttribute("book", bookDto);
        List<AuthorDto> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        List<GenreDto> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "book/edit";
    }

    @GetMapping("/book/create")
    public String bookCreate(Model model) {
        BookDto bookDto = new BookDto();
        model.addAttribute("book", bookDto);

        List<AuthorDto> authors = authorService.findAll();
        model.addAttribute("authors", authors);

        List<GenreDto> genres = genreService.findAll();
        model.addAttribute("genres", genres);

        return "book/create";
    }

    @PostMapping("/book")
    public String bookCreate(@ModelAttribute BookDto book) {

        bookService.insert(book);
        return "redirect:/book/list";
    }

    @PostMapping(value = "/book", params = {"id"})
    public String bookUpdate(@RequestParam long id, @ModelAttribute BookDto bookDto) {

        bookDto.setId(id);
        bookService.update(bookDto);
        return "redirect:/book/list";
    }

    @PostMapping("/book/{id}")
    public String bookDelete(@PathVariable long id) {
        bookService.delete(id);
        return "redirect:/book/list";
    }

}
