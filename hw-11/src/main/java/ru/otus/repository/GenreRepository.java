package ru.otus.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.otus.domain.Genre;

import java.util.List;

public interface GenreRepository extends ReactiveCrudRepository<Genre, String> {

    Flux<Genre> findByNameContainingIgnoreCase(String name);
}
