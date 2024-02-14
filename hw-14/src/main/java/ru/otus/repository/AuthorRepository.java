package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.mongo.mAuthor;

import java.util.List;

public interface AuthorRepository extends MongoRepository<mAuthor, String> {

    List<mAuthor> findByNameContainingIgnoreCase(String name);
}
