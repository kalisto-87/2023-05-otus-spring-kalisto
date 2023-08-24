package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    public List<Comment> findCommentsByBook(long bookId) {
        return commentRepository.getCommentsByBook(bookId);
    }

    @Override
    public Comment findById(long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new DataNotFoundException(
                String.format("Comment with id =%s not found", commentId)));
    }

    @Override
    public Comment insert(Comment comment) {
        Book book = bookRepository.findById(comment.getBook().getId()).
                orElseThrow(() -> new DataNotFoundException(
                        String.format("Book with id =%s not found", comment.getBook().getId())));
        return commentRepository.insert(comment);
    }

    @Override
    public Comment update(long commentId, String Text) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new DataNotFoundException(String.format("Comment with id=%s not found", commentId)));
        comment.setText(Text);
        return commentRepository.update(comment);
    }

    @Override
    public void delete(long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new DataNotFoundException(String.format("Comment with id=%s not found", commentId)));
        commentRepository.delete(comment);
    }
}
