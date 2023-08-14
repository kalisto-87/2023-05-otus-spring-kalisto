package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.exception.DataNotFoundException;
import ru.otus.domain.Author;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;

    @Override
    public List<Author> findByName(String authorName) {
        return dao.findByName(authorName);
    }

    @Override
    public List<Author> getAll() {
        return dao.getAll();
    }

    @Override
    public Author findById(Long authorId) {
        return dao.findById(authorId).orElseThrow(() -> new DataNotFoundException(String.format("Author with id=%s not found!", authorId)));
    }

    @Override
    public Author insert(String authorName) {
        return dao.insertAuthor(authorName);
    }

    @Override
    public boolean update(Long id, String authorName) {
        return dao.updateAuthor(id, authorName);
    }

    @Override
    public boolean delete(Long id) {
        return dao.deleteAuthor(id);
    }

}
