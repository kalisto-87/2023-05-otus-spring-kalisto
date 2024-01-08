package ru.otus.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    Flux<AuthorDto> findAll();

    Flux<AuthorDto> findByName(String authorName);

    Flux<AuthorDto> findByIds(List<String> authorsId);

    Mono<AuthorDto> findById(String authorId);

    Mono<AuthorDto> insert(AuthorDto authorDto);

    Mono<AuthorDto> update(AuthorDto authorDto);

    Mono<Void> delete(String authorId);
}
