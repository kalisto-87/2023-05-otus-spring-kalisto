package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public Comment insert(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public Comment update(long commentId, String Text) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new DataNotFoundException(String.format("Comment with id=%s not found", commentId)));
        comment.setText(Text);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void delete(long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    public void deleteByBook(Book book) {
        commentRepository.deleteByBook(book);
    }
}
