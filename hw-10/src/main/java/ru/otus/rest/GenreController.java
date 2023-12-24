package ru.otus.rest;

import org.springframework.web.bind.annotation.*;
import ru.otus.dto.GenreDto;
import ru.otus.service.GenreService;

import java.util.List;

@RestController
@RequestMapping
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("api/genre")
    public List<GenreDto> getGenres() {
        return genreService.findAll();
    }

    @GetMapping("api/genre/{id}")
    public GenreDto getGenre(@PathVariable("id") long genreId) {
        return genreService.findById(genreId);
    }

    @PostMapping("api/genre")
    public GenreDto createAuthor(@RequestBody GenreDto genreDto) {
        return genreService.insert(genreDto);
    }
}
