package ru.otus.service.transform;

public interface CommentConvertToUI {

    String findCommentsByBook(String bookId);

    String insert(String bookId, String text);

    String update(String commentId, String text);

    String delete(String commentId);

    String deleteByBook(String bookId);
}
