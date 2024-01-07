package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Author;
import ru.otus.dto.AuthorDto;
import ru.otus.exception.DataNotFoundException;
import ru.otus.mapper.AuthorMapper;
import ru.otus.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authorMapper.toDtoList(authors);
    }

    @Override
    public List<AuthorDto> findByName(String authorName) {
        List<Author> authors = authorRepository.findByNameContainingIgnoreCase(authorName);
        return authorMapper.toDtoList(authors);
    }

    @Override
    public List<AuthorDto> findByIds(List<String> authorsId) {
        List<Author> authors = authorsId.stream().map(m -> authorRepository.findById(m).orElseThrow(
                () -> new DataNotFoundException(String.format("Author with id=%s not found", m))
        )).toList();
        return authorMapper.toDtoList(authors);
    }

    @Override
    public AuthorDto findById(String authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new DataNotFoundException(String.format("Author with id=%s not found", authorId)));
        return authorMapper.toDto(author);
    }

    @Override
    public AuthorDto insert(AuthorDto authorDto) {
        Author newAuthor = authorMapper.toDomain(authorDto);
        authorRepository.save(newAuthor);
        return authorMapper.toDto(newAuthor);
    }

    @Override
    public AuthorDto update(AuthorDto authorDto) {
        Author author = authorRepository.findById(authorDto.getId()).orElseThrow(
                () -> new DataNotFoundException(String.format("Author with id=%s not found", authorDto.getId())));
        String name = authorDto.getName();
        if (name != null && !name.isEmpty()) {
            author.setName(name);
        }
        authorRepository.save(author);
        return authorMapper.toDto(author);
    }

    @Override
    public void delete(String authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(
                () -> new DataNotFoundException(String.format("Author with id=%s not found", authorId)));
        authorRepository.delete(author);
    }
}
