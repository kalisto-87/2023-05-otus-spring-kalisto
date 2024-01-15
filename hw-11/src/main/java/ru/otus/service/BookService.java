package ru.otus.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dto.BookDto;

public interface BookService {

    Flux<BookDto> findAll();

    Flux<BookDto> findByName(String bookName);

    Mono<BookDto> findById(String bookId);

    /*List<BookDto> findByAuthorId(AuthorDto authorDto);

    List<BookDto> findByGenreId(GenreDto genreDto);*/

    Mono<BookDto> insert(BookDto bookDto);

    Mono<BookDto> update(BookDto bookDto);

    Mono<Void> delete(String bookId);
}
