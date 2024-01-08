package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;
import ru.otus.exception.DataNotFoundException;
import ru.otus.mapper.BookMapper;
import ru.otus.mapper.CommentMapper;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private final CommentMapper commentMapper;

    private final BookMapper bookMapper;

    @Override
    public Flux<CommentDto> findCommentsByBook(BookDto bookDto) {
        Mono<Book> book = bookRepository.findById(bookDto.getId()).switchIfEmpty(
                Mono.error(new DataNotFoundException(String.format("Book with id=%s not found", bookDto.getId()))));

        return commentRepository.findByBookId(bookDto.getId()).map(c -> commentMapper.toDto(c));
    }

    @Override
    public Mono<CommentDto> insert(CommentDto commentDto, String bookId) {
        Comment newComment = commentMapper.toDomain(commentDto);
        Mono<Book> book = bookRepository.findById(bookId);
        newComment.setBookId(bookId);
        return commentRepository.save(newComment).map(c -> commentMapper.toDto(c));
    }

    @Override
    public Mono<Void> delete(String commentId) {
        return commentRepository.deleteById(commentId);
    }

    @Override
    public Mono<Void> deleteByBookId(String bookId) {
        return commentRepository.deleteCommentByBookId(bookId);
    }
}
