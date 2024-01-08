package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;
import ru.otus.exception.DataNotFoundException;
import ru.otus.mapper.BookMapper;
import ru.otus.mapper.CommentMapper;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private final CommentMapper commentMapper;

    private final BookMapper bookMapper;

    @Override
    public List<CommentDto> findCommentsByBook(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getId()).orElseThrow(
                () -> new DataNotFoundException(String.format("Book with id=%s not found", bookDto.getId())));
        return commentMapper.toDtoList(book.getComments());
    }

    @Override
    public CommentDto insert(CommentDto commentDto, Long bookId) {
        Comment newComment = commentMapper.toDomain(commentDto);
        Optional<Book> book = bookRepository.findById(bookId);
        book.ifPresentOrElse(newComment::setBook,
                () -> new DataNotFoundException(String.format("Book with id=%s", bookId)));
        commentRepository.save(newComment);
        return commentMapper.toDto(newComment);
    }

    @Override
    public CommentDto update(CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentDto.getId()).orElseThrow(() ->
                new DataNotFoundException(String.format("Comment with id=%s not found", commentDto.getId())));
        String text = commentDto.getText();
        if (text != null && !text.isEmpty()) {
            comment.setText(text);
        }
        comment.setText(text);
        commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    @Override
    public void delete(long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public void deleteByBook(BookDto bookDto) {
        commentRepository.deleteByBook(bookMapper.toDomain(bookDto));
    }
}
