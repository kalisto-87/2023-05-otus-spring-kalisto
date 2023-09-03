package ru.otus.service.transfer;

public interface CommentConvertToUI {

    String findCommentsByBook(long bookId);

    String insert(long bookId, String Text);

    String update(long commentId, String Text);

    String delete(long commentId);

}
