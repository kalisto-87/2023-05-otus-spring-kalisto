package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.mongo.mBook;

import java.util.List;

public interface BookRepository extends MongoRepository<mBook, String > {

    List<mBook> findByTitleContainingIgnoreCase(String name);

    List<mBook> findBookByAuthorsId(String id);

    List<mBook> findBookByGenresId(String id);
}
