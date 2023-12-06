package ru.otus.service;

import ru.otus.domain.Comment;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> findCommentsByBook(BookDto bookDto);

    Comment findCommentById(long commentId);

    CommentDto insert(CommentDto commentDto, Long bookId);

    CommentDto update(CommentDto commentDto);

    void delete(long commentId);

    void deleteByBook(BookDto bookDto);
}
