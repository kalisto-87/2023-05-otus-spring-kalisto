package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.converter.CommentConverter;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private static final String INSERT_RECORD = "New record has been created in the library %s";

    private static final String UPDATE_RECORD = "The record with id=%s has been updated";

    private static final String DELETE_RECORD = "The record with id=%s has been deleted from the library";

    private static final String UNSUCCESSFUL_CHANGES = "The record hasn't been changed";

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private final CommentConverter commentConverter;

    public String findCommentsByBook(long bookId) {
        return commentRepository.getCommentsByBook(bookId).stream().map(com -> commentConverter.convert(com)).
                collect(Collectors.joining("\n"));
    }

    @Override
    public String insert(long bookId, String Text) {
        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new DataNotFoundException(
                        String.format("Book with id =%s not found", bookId)));
        Comment comment = new Comment(0, Text, book);
        comment = commentRepository.insert(comment);
        return String.format(INSERT_RECORD, commentConverter.convert(comment));
    }

    @Override
    public String update(long commentId, String Text) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new DataNotFoundException(String.format("Comment with id=%s not found", commentId)));
        comment.setText(Text);
        if (commentRepository.update(comment)) {
            return String.format(UPDATE_RECORD, commentId);
        }
        return UNSUCCESSFUL_CHANGES;
    }

    @Override
    public String delete(long authorId) {
        if (commentRepository.delete(authorId)) {
            return String.format(DELETE_RECORD, authorId);
        }
        return UNSUCCESSFUL_CHANGES;
    }
}
