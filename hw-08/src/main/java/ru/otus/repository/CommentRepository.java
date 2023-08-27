package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String > {

    List<Comment> findAllByBookId(String bookId);

    void deleteCommentByBookId(String bookId);
}
