package ru.otus.service.transform;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.converter.CommentConverter;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.exception.DataNotFoundException;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentConvertToUIImpl implements CommentConvertToUI {

    private static final String INSERT_RECORD = "New record has been created in the library %s";

    private static final String UPDATE_RECORD = "The comment with id=%s has been updated";

    private static final String DELETE_RECORD = "The comment with id=%s has been deleted from the library";

    private static final String DELETE_BY_BOOK =
            "The comments of the book with id=%s have been deleted from the library";

    private final CommentService commentService;

    private final BookService bookService;

    private final CommentConverter commentConverter;

    @Override
    @Transactional(readOnly = true)
    public String findCommentsByBook(String bookId) {
        try {
            Book book = bookService.findById(bookId).orElseThrow(() ->
                    new DataNotFoundException(String.format("Book with id =%s not found", bookId)));
            return commentService.findCommentsByBook(book).stream().map(com -> commentConverter.convert(com)).
                    collect(Collectors.joining("\n"));
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }

    @Override
    public String insert(String bookId, String text) {
        try {
            Book book = bookService.findById(bookId).orElseThrow(() -> new DataNotFoundException(
                    String.format("Book with id=%s not found", bookId)));
            Comment comment = new Comment(text, book.getId());
            comment = commentService.insert(comment);
            return String.format(INSERT_RECORD, commentConverter.convert(comment));
        } catch (DataNotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public String update(String commentId, String text) {
        try {
            commentService.update(commentId, text);
            return String.format(UPDATE_RECORD, commentId);
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }

    @Override
    public String delete(String commentId) {
        try {
            commentService.delete(commentId);
            return String.format(DELETE_RECORD, commentId);
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }

    @Override
    public String deleteByBook(String bookId) {
        try {
            Book book = bookService.findById(bookId).orElseThrow(() -> new DataNotFoundException(
                    String.format("Book with id=%s not found", bookId)));
            commentService.deleteByBook(book);
            return String.format(DELETE_BY_BOOK, bookId);
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }
}
