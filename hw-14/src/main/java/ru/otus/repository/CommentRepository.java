package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.mongo.MongoComment;

import java.util.List;

public interface CommentRepository extends MongoRepository<MongoComment, String > {

    List<MongoComment> findByBookId(String bookId);

    void deleteCommentByBookId(String bookId);
}
