package ru.otus.service;

import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findCommentsByBook(Book book);

    Comment insert(Comment comment);

    Comment update(String commentId, String Text);

    void delete(String commentId);

    //void deleteByBook(Book book);
}
