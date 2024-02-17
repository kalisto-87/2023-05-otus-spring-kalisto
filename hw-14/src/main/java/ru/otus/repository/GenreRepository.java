package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.mongo.MongoGenre;

import java.util.List;

public interface GenreRepository extends MongoRepository<MongoGenre, String> {

    List<MongoGenre> findByNameContainingIgnoreCase(String name);
}
