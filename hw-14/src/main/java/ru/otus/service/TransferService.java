package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.h2.Author;
import ru.otus.domain.h2.Genre;
import ru.otus.domain.mongo.mAuthor;
import ru.otus.domain.mongo.mGenre;

@Service
public class TransferService {

    public mAuthor transferAuthor(Author author) {
        return new mAuthor(author.getId(), author.getName());
    }

    public mGenre transferGenre(Genre genre) {
        return new mGenre(genre.getId(), genre.getName());
    }
}
