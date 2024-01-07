package ru.otus.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import ru.otus.domain.Book;

import java.util.List;

public interface BookRepository extends ReactiveCrudRepository<Book, String > {

    Flux<Book> findByTitleContainingIgnoreCase(String name);

    Flux<Book> findBookByAuthorsId(String id);

    Flux<Book> findBookByGenresId(String id);
}
