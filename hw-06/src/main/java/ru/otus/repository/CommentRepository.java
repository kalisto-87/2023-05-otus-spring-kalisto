package ru.otus.repository;

import ru.otus.domain.Comment;

import java.util.Optional;

public interface CommentRepository {

    Optional<Comment> findById(long commentId);

    Comment insert(Comment comment);

    Comment update(Comment comment);

    void delete(Comment comment);
}
