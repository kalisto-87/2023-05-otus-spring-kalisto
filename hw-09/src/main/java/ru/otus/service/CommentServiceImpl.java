package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.exception.DataNotFoundException;
import ru.otus.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> findCommentsByBook(Book book) {
        return book.getComments();
    }

    @Override
    public Comment insert(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(long commentId, String text) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new DataNotFoundException(String.format("Comment with id=%s not found", commentId)));
        comment.setText(text);
        return commentRepository.save(comment);
    }

    @Override
    public void delete(long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public void deleteByBook(Book book) {
        commentRepository.deleteByBook(book);
    }
}
