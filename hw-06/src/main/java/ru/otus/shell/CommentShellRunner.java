package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.service.transfer.CommentConvertToUI;

@ShellComponent
@RequiredArgsConstructor
public class CommentShellRunner {

    private final CommentConvertToUI convert;

    @ShellMethod(value = "get comments book", key = {"com-b-a", "comment-book-all"})
    public String getBookComments(@ShellOption long bookId) {
        return convert.findCommentsByBook(bookId);
    }

    @ShellMethod(value = "add comment to the book", key = {"com-b-add", "comment-book-add"})
    public String addComment(@ShellOption long bookId, @ShellOption String text) {
        return convert.insert(bookId, text);
    }

    @ShellMethod(value = "update comment by id", key = {"com-u", "comment-update"})
    public String changeComment(@ShellOption long commentId, @ShellOption String text) {
        return convert.update(commentId, text);
    }

    @ShellMethod(value = "add comment by id", key = {"com-d", "comment-delete"})
    public String deleteComment(@ShellOption long commentId) {
        return convert.delete(commentId);
    }
}
