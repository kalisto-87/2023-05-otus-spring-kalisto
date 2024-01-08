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
import ru.otus.dto.GenreDto;
import ru.otus.service.GenreService;

@RestController
@RequestMapping
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("api/genre")
    public Flux<GenreDto> getGenres() {
        return genreService.findAll();
    }

    @GetMapping("api/genre/{id}")
    public Mono<GenreDto> getGenre(@PathVariable("id") String genreId) {
        return genreService.findById(genreId);
    }

    @PostMapping("api/genre")
    public Mono<GenreDto> createAuthor(@RequestBody GenreDto genreDto) {
        return genreService.insert(genreDto);
    }

    @PatchMapping("api/genre")
    public Mono<GenreDto> editGenre(@RequestBody GenreDto genreDto) {
        return genreService.update(genreDto);
    }

    @DeleteMapping("api/genre/{id}")
    public void deleteGenre(@PathVariable("id") String genreId) {
        Mono<GenreDto> genre = genreService.findById(genreId);
        if (genre != null) {
            genreService.delete(genreId).block();
        }
    }


}
