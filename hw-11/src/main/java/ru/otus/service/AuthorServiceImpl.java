package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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
    public Flux<AuthorDto> findAll() {
        return authorRepository.findAll().map(a -> authorMapper.toDto(a));
    }

    @Override
    public Flux<AuthorDto> findByName(String authorName) {
        return authorRepository.findByNameContainingIgnoreCase(authorName).map(a -> authorMapper.toDto(a));
    }

    @Override
    public Flux<AuthorDto> findByIds(List<String> authorsId) {

        Flux<AuthorDto> authors = null;
        for (String s : authorsId
        ) {
            Mono<AuthorDto> author = authorRepository.findById(s).map(a -> authorMapper.toDto(a));
            authors = Flux.merge(author);
        }
        return authors;
    }

    @Override
    public Mono<AuthorDto> findById(String authorId) {
        return authorRepository.findById(authorId).map(m -> authorMapper.toDto(m));
    }

    @Override
    public Mono<AuthorDto> insert(AuthorDto authorDto) {
        Author newAuthor = authorMapper.toDomain(authorDto);
        return authorRepository.save(newAuthor).map(m -> authorMapper.toDto(m));
    }

    @Override
    public Mono<AuthorDto> update(AuthorDto authorDto) {
        Mono<Author> author = authorRepository.findById(authorDto.getId()).switchIfEmpty(
                Mono.error(new DataNotFoundException(String.format("Author with id=%s not found", authorDto.getId()))));
        String name = authorDto.getName();
        if (name != null && !name.isEmpty()) {
            Mono<Author> newAuthor = author.map(g -> {
                return new Author(authorDto.getId(), g.getName());
            });
            Author a = newAuthor.block();
            return authorRepository.save(a).map(g -> authorMapper.toDto(g));
        }
        return author.map(m -> authorMapper.toDto(m));
    }

    @Override
    public Mono<Void> delete(String authorId) {
        Mono<Author> author = authorRepository.findById(authorId).switchIfEmpty(
                Mono.error(new DataNotFoundException(String.format("Author with id=%s not found", authorId))));
        return authorRepository.deleteById(authorId);
    }
}
