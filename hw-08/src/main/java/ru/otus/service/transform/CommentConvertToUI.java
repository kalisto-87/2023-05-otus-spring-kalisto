package ru.otus.service.transform;

public interface CommentConvertToUI {

    String findCommentsByBook(String bookId);

    String insert(String bookId, String Text);

    String update(String commentId, String Text);

    String delete(String commentId);

    String deleteByBook(String bookId);
}
