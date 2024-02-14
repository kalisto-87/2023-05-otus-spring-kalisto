package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.mongo.mGenre;

import java.util.List;

public interface GenreRepository extends MongoRepository<mGenre, String> {

    List<mGenre> findByNameContainingIgnoreCase(String name);
}
