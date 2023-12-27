package ru.otus.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PatchMapping("api/genre/{id}")
    public GenreDto editGenre(@RequestBody GenreDto genreDto) {
        return genreService.update(genreDto);
    }

    @DeleteMapping("api/genre/{id}")
    public void deleteGenre(@PathVariable("id") long genreId) {
        genreService.delete(genreId);
    }


}
