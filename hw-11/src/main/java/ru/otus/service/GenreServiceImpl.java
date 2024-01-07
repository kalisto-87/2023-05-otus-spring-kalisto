package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Genre;
import ru.otus.dto.GenreDto;
import ru.otus.exception.DataNotFoundException;
import ru.otus.mapper.GenreMapper;
import ru.otus.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    private final GenreMapper genreMapper;

    @Override
    public List<GenreDto> findAll() {
        List<Genre> genres = genreRepository.findAll();
        return genreMapper.toDtoList(genres);
    }

    @Override
    public List<GenreDto> findByName(String genreName) {
        List<Genre> genres = genreRepository.findByNameContainingIgnoreCase(genreName);
        return genreMapper.toDtoList(genres);
    }

    @Override
    public List<GenreDto> findByIds(List<String> genresId) {
        List<Genre> genres = genresId.stream().map(m -> genreRepository.findById(m).orElseThrow(
                () -> new DataNotFoundException(String.format("Genre with id=%s not found", m))
        )).toList();
        return genreMapper.toDtoList(genres);
    }

    @Override
    public GenreDto findById(String genreId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(
                () -> new DataNotFoundException(String.format("Genre with id=%s not found", genreId)));
        return genreMapper.toDto(genre);
    }

    @Override
    @Transactional
    public GenreDto insert(GenreDto genreDto) {
        Genre newGenre = genreMapper.toDomain(genreDto);
        genreRepository.save(newGenre);
        return genreMapper.toDto(newGenre);
    }

    @Override
    @Transactional
    public GenreDto update(GenreDto genreDto) {
        Genre genre = genreRepository.findById(genreDto.getId()).orElseThrow(
                () -> new DataNotFoundException(String.format("Genre with id=%s not found", genreDto.getId())));
        String name = genreDto.getName();
        if (name != null && !name.isEmpty()) {
            genre.setName(name);
        }
        genreRepository.save(genre);
        return genreMapper.toDto(genre);
    }

    @Override
    @Transactional
    public void delete(String genreId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(
                () -> new DataNotFoundException(String.format("Genre with id=%s not found", genreId)));
        genreRepository.delete(genre);
    }
}
