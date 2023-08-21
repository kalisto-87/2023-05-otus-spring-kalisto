package ru.otus.service;

public interface CommentService {

    String findCommentsByBook(long bookId);

    String insert(long bookId, String Text);

    String update(long commentId, String Text);

    String delete(long authorId);
}
