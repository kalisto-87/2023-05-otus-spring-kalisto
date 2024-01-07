package ru.otus.service;

import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> findCommentsByBook(BookDto bookDto);

    CommentDto insert(CommentDto commentDto, String bookId);

    CommentDto update(CommentDto commentDto);

    void delete(String commentId);

    void deleteByBook(BookDto bookDto);
}
