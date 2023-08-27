package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.service.transform.CommentConvertToUI;

@ShellComponent
@RequiredArgsConstructor
public class CommentShellRunner {

    private final CommentConvertToUI commentConvertToUI;

    @ShellMethod(value = "get comments by book", key = {"com-b-a", "comment-book-all"})
    public String getBookComments(@ShellOption long bookId) {
        return commentConvertToUI.findCommentsByBook(bookId);
    }

    @ShellMethod(value = "add comment to the book", key = {"com-b-add", "comment-book-add"})
    public String addComment(@ShellOption long bookId, @ShellOption String text) {
        return commentConvertToUI.insert(bookId, text);
    }

    @ShellMethod(value = "update comment by id", key = {"com-u", "comment-update"})
    public String changeComment(@ShellOption long commentId, @ShellOption String text) {
        return commentConvertToUI.update(commentId, text);
    }

    @ShellMethod(value = "delete comment by id", key = {"com-d", "comment-delete"})
    public String deleteComment(@ShellOption long commentId) {
        return commentConvertToUI.delete(commentId);
    }

    @ShellMethod(value = "delete comment by book", key = {"com-d-b", "comment-delete-book"})
    public String deleteCommentByBook(@ShellOption long bookId) {
        return commentConvertToUI.deleteByBook(bookId);
    }
}
