package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.otus.repository.BookRepository;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository repository;

}
