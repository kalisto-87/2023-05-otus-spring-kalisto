package ru.otus.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public AuthorDto getAuhtor(@PathVariable("id") String authorId) {
        return authorService.findById(authorId);
    }

    @PostMapping("api/author")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.insert(authorDto);
    }

    @PatchMapping("api/author")
    public AuthorDto editAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.update(authorDto);
    }

    @DeleteMapping("api/author/{id}")
    public void deleteAuthor(@PathVariable("id") String authorId) {
        authorService.delete(authorId);
    }
}
