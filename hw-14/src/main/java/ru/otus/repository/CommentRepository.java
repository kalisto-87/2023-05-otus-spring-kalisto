package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.mongo.mComment;

import java.util.List;

public interface CommentRepository extends MongoRepository<mComment, String > {

    List<mComment> findByBookId(String bookId);

    void deleteCommentByBookId(String bookId);
}
