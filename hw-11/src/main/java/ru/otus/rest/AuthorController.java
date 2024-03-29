package ru.otus.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dto.AuthorDto;
import ru.otus.service.AuthorService;

@RestController
@RequestMapping
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("api/author")
    public Flux<AuthorDto> getBooks() {
        return authorService.findAll();
    }

    @GetMapping("api/author/{id}")
    public Mono<AuthorDto> getAuhtor(@PathVariable("id") String authorId) {
        return authorService.findById(authorId);
    }

    @PostMapping("api/author")
    public Mono<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.insert(authorDto);
    }

    @PatchMapping("api/author")
    public Mono<AuthorDto> editAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.update(authorDto);
    }

    @DeleteMapping("api/author/{id}")
    public void deleteAuthor(@PathVariable("id") String authorId) {
        Mono<AuthorDto> author = authorService.findById(authorId);
        if (author != null) {
            authorService.delete(authorId).block();
        }
    }
}
