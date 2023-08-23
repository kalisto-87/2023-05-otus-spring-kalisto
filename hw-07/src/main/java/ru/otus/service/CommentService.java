package ru.otus.service;

import ru.otus.domain.Comment;

public interface CommentService {

    String findCommentsByBook(long bookId);

    Comment insert(long bookId, String Text);

    Comment update(long commentId, String Text);

    void delete(long authorId);

    void deleteByBook(long bookId);
}
