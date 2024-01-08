package ru.otus.repository;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Author;


public interface AuthorRepository extends ReactiveCrudRepository<Author, String> {

    Flux<Author> findByNameContainingIgnoreCase(String name);

    Mono<Author> save(Author author);

}
