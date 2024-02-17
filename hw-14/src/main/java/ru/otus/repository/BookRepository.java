package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.mongo.MongoBook;

import java.util.List;

public interface BookRepository extends MongoRepository<MongoBook, String > {

    List<MongoBook> findByTitleContainingIgnoreCase(String name);

    List<MongoBook> findBookByAuthorsId(String id);

    List<MongoBook> findBookByGenresId(String id);
}
