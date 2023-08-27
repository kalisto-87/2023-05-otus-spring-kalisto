package ru.otus.service.transform;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
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
    public String findCommentsByBook(long bookId) {
        try {
            Book book = bookService.findById(bookId).orElseThrow(() ->
                    new DataNotFoundException(String.format("Book with id =%s not found", bookId)));
            Hibernate.initialize(book.getComments());
            return book.getComments().stream().map(com -> commentConverter.convert(com)).
                    collect(Collectors.joining("\n"));
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }

    @Override
    public String insert(long bookId, String Text) {
        try {
            Book book = bookService.findById(bookId).orElseThrow(() -> new DataNotFoundException(
                    String.format("Book with id=%s not found", bookId)));
            Comment comment = new Comment(0, Text, book);
            comment = commentService.insert(comment);
            return String.format(INSERT_RECORD, commentConverter.convert(comment));
        } catch (DataNotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public String update(long commentId, String Text) {
        try {
            commentService.update(commentId, Text);
            return String.format(UPDATE_RECORD, commentId);
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }

    @Override
    public String delete(long commentId) {
        try {
            commentService.delete(commentId);
            return String.format(DELETE_RECORD, commentId);
        } catch (DataAccessException e) {
            return e.getMessage();
        }
    }

    @Override
    public String deleteByBook(long bookId) {
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
