package ru.otus.rest;

import org.springframework.web.bind.annotation.*;
import ru.otus.dto.AuthorDto;
import ru.otus.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("api/author")
    public List<AuthorDto> getBooks() {
        return authorService.findAll();
    }

    @GetMapping("api/author/{id}")
    public AuthorDto getAuhtor(@PathVariable("id") long authorId) {
        return authorService.findById(authorId);
    }

    @PostMapping("api/author")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.insert(authorDto);
    }

    @PutMapping("api/author/{id}")
    public void editAuthor(@RequestBody AuthorDto authorDto) {
        authorService.update(authorDto);
    }

    @DeleteMapping("api/author/{id}")
    public void deleteAuthor(@PathVariable("id") long authorId) {
        authorService.delete(authorId);
    }
}
