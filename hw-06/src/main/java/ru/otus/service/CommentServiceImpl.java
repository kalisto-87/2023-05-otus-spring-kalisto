package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Override
    @Transactional
    public List<Comment> findCommentsByBook(long bookId) {

        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new DataNotFoundException(String.format("Book with id =%s not found", bookId)));
        Hibernate.initialize(book.getComments());
        return book.getComments();
    }

    @Override
    public Comment findById(long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new DataNotFoundException(
                String.format("Comment with id =%s not found", commentId)));
    }

    @Override
    @Transactional
    public Comment insert(Comment comment) {
        Book book = bookRepository.findById(comment.getBook().getId()).
                orElseThrow(() -> new DataNotFoundException(
                        String.format("Book with id =%s not found", comment.getBook().getId())));
        return commentRepository.insert(comment);
    }

    @Override
    @Transactional
    public Comment update(long commentId, String Text) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new DataNotFoundException(String.format("Comment with id=%s not found", commentId)));
        comment.setText(Text);
        return commentRepository.update(comment);
    }

    @Override
    @Transactional
    public void delete(long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new DataNotFoundException(String.format("Comment with id=%s not found", commentId)));
        commentRepository.delete(comment);
    }
}
