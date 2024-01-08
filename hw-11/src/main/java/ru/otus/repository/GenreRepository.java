package ru.otus.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Genre;

public interface GenreRepository extends ReactiveCrudRepository<Genre, String> {

    Flux<Genre> findByNameContainingIgnoreCase(String name);

    Mono<Genre> save(Genre genre);
}
