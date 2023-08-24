package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.converter.CommentConverter;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private static final String LIST_OF_COMMENTS = "Comments for book %s\n%s";

    private static final String CREATED_COMMENT = "Comment has been created with id=%s";

    private static final String CHANGE_SUCCESSFUL = "Сhanges have been successfully implemented";

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private final CommentConverter commentConverter;

    @Override
    public String findCommentsByBook(long bookId) {
        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new DataNotFoundException(
                        String.format("Book with id =%s not found", bookId)));
        List<Comment> comments = commentRepository.findByBook(book);
        return String.format(LIST_OF_COMMENTS, book.getTitle(),
                comments.stream().map(com -> commentConverter.convert(com)).
                collect(Collectors.joining("\n")));
    }

    @Override
    public String insert(long bookId, String Text) {
        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new DataNotFoundException(
                        String.format("Book with id =%s not found", bookId)));
        Comment comment = new Comment(0, Text, book);
        comment = commentRepository.save(comment);
        return String.format(CREATED_COMMENT, comment.getId());
    }

    @Override
    public String update(long commentId, String Text) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new DataNotFoundException(String.format("Comment with id=%s not found", commentId)));
        comment.setText(Text);
        commentRepository.save(comment);
        return CHANGE_SUCCESSFUL;
    }

    @Override
    public String delete(long authorId) {
        commentRepository.deleteById(authorId);
        return CHANGE_SUCCESSFUL;
    }

    @Override
    public String deleteByBook(long bookId) {
        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new DataNotFoundException(
                        String.format("Book with id =%s not found", bookId)));
        commentRepository.deleteByBook(book);
        return CHANGE_SUCCESSFUL;
    }
}
