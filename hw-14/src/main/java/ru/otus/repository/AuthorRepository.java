package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.mongo.MongoAuthor;

import java.util.List;

public interface AuthorRepository extends MongoRepository<MongoAuthor, String> {

    List<MongoAuthor> findByNameContainingIgnoreCase(String name);
}
