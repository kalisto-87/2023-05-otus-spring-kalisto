package ru.otus.repository;

import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    List<Comment> getCommentsByBook(long bookId);

    Optional<Comment> findById(long commentId);

    Comment insert(Comment comment);

    Comment update(Comment comment);

    void delete(Comment comment);
}
