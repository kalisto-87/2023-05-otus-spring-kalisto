package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.GenreDao;
import ru.otus.dao.exception.DataNotFoundException;
import ru.otus.domain.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao dao;

    @Override
    public List<Genre> findByName(String genreName) {
        return dao.findByName(genreName);
    }

    @Override
    public List<Genre> getAll() {
        return dao.getAll();
    }

    @Override
    public Genre findById(Long genreId) {
        return dao.findById(genreId).orElseThrow(() -> new DataNotFoundException(String.
                format("Genre with id=%s not found", genreId)));
    }

    @Override
    public Genre insert(String genreName) {
        return dao.insertGenre(genreName);
    }

    @Override
    public boolean update(Long genreId, String genreName) {
        return dao.updateGenre(genreId, genreName);
    }

    @Override
    public boolean delete(Long genreId) {
        return dao.deleteGenre(genreId);
    }
}
