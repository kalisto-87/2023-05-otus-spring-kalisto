package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.domain.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String > {

    List<Book> findAll();

    List<Book> findByTitleContainingIgnoreCase(String name);

    List<Book> findBookByAuthorsId(String id);

    List<Book> findBookByGenresId(String id);
}
