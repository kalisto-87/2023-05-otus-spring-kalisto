package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Genre;

import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {

    List<Genre> findByNameContainingIgnoreCase(String name);
}
