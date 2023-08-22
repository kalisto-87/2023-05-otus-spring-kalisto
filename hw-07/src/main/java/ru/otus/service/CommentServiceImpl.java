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

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private final CommentConverter commentConverter;

    public String findCommentsByBook(long bookId) {
        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new DataNotFoundException(
                        String.format("Book with id =%s not found", bookId)));
        List<Comment> comments = commentRepository.findByBook(book);
        return commentRepository.findByBook(book).stream().map(com -> commentConverter.convert(com)).
                collect(Collectors.joining("\n"));
    }

    @Override
    public Comment insert(long bookId, String Text) {
        Book book = bookRepository.findById(bookId).
                orElseThrow(() -> new DataNotFoundException(
                        String.format("Book with id =%s not found", bookId)));
        Comment comment = new Comment(0, Text, book);
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(long commentId, String Text) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new DataNotFoundException(String.format("Comment with id=%s not found", commentId)));
        comment.setText(Text);
        return commentRepository.save(comment);
    }

    @Override
    public void delete(long authorId) {
        commentRepository.deleteById(authorId);
    }
}
