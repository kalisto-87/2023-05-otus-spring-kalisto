package ru.otus.repository;

import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    List<Comment> getCommentsByBook(long bookId);

    Optional<Comment> findById(long commentId);

    Comment insert(Comment comment);

    boolean update(Comment comment);

    boolean delete(long commentId);
}
