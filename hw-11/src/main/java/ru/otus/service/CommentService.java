package ru.otus.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;

public interface CommentService {

    Flux<CommentDto> findCommentsByBook(BookDto bookDto);

    Mono<CommentDto> insert(CommentDto commentDto, String bookId);

    Mono<Void> delete(String commentId);

    Mono<Void> deleteByBookId(String bookId);
}
