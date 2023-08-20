package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.converter.BookConverter;
import ru.otus.repository.BookRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private static final String LIST_OF_ALL_BOOKS = "Список всех книг:\n";

    private final BookRepository bookRepository;

    private final BookConverter bookConverter;

    @Override
    public String findAll() {
        return LIST_OF_ALL_BOOKS +
                bookRepository.findAll().stream().map(book -> bookConverter.convert(book)).
                        collect(Collectors.joining("\n"));
    }
}
