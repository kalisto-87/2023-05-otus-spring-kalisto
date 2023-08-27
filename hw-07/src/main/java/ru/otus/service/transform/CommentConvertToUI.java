package ru.otus.service.transform;

public interface CommentConvertToUI {

    String findCommentsByBook(long bookId);

    String insert(long bookId, String Text);

    String update(long commentId, String Text);

    String delete(long commentId);

    String deleteByBook(long bookId);
}
