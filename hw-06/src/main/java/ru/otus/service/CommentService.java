package ru.otus.service;

import ru.otus.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findCommentsByBook(long bookId);

    Comment findById(long commentId);

    Comment insert(Comment comment);

    Comment update(long commentId, String Text);

    void delete(long commentId);
}
