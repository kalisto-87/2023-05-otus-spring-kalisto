package ru.otus.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Comment;

import java.util.List;

public interface CommentRepository extends ReactiveCrudRepository<Comment, String > {

    Flux<Comment> findByBookId(String bookId);

    Mono<Void> deleteCommentByBookId(String bookId);
}
