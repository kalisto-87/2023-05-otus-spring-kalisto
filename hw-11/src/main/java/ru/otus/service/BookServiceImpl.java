package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Book;
import ru.otus.dto.BookDto;
import ru.otus.exception.DataNotFoundException;
import ru.otus.mapper.BookMapper;
import ru.otus.repository.BookRepository;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Override
    public Flux<BookDto> findAll() {
        return bookRepository.findAll().map(b -> bookMapper.toDto(b));
    }

    @Override
    public Flux<BookDto> findByName(String bookName) {
        return bookRepository.findByTitleContainingIgnoreCase(bookName).map(b -> bookMapper.toDto(b));
    }

    @Override
    public Mono<BookDto> findById(String bookId) {
        Mono<Book> book = bookRepository.findById(bookId)
                .switchIfEmpty(Mono.error(new DataNotFoundException(String.format("Book with id=%s", bookId))));
        return book.map(b -> bookMapper.toDto(b));
    }

    /*@Override
    public List<BookDto> findByAuthorId(AuthorDto authorDto) {
        List<Book> books = bookRepository.findByAuthors(authorMapper.toDomain(authorDto));
        return bookMapper.toDtoList(books);
    }

    @Override
    public List<BookDto> findByGenreId(GenreDto genreDto) {
        List<Book> books = bookRepository.findByGenres(genreMapper.toDomain(genreDto));
        return bookMapper.toDtoList(books);
    }*/

    @Override
    @Transactional
    public Mono<BookDto> insert(BookDto bookDto) {
        Book newBook = bookMapper.toDomain(bookDto);
        if (bookDto.getAuthors() == null || bookDto.getAuthors().size() == 0) {
            throw new DataNotFoundException("List of authors is empty");
        }
        if (bookDto.getGenres() == null || bookDto.getGenres().size() == 0) {
            throw new DataNotFoundException("List of genres is empty");
        }
        return bookRepository.save(newBook).map(b -> bookMapper.toDto(b));
    }

    @Override
    @Transactional
    public Mono<BookDto> update(BookDto bookDto) {
        Mono<Book> mbook = bookRepository.findById(bookDto.getId()).switchIfEmpty(
                Mono.error(new DataNotFoundException(String.format("Book with id=%s not found", bookDto.getId()))));

        Book book = mbook.block();
        String title = bookDto.getTitle();
        if (title != null && !title.isEmpty()) {
            book.setTitle(title);
        }

        if (book.getAuthors() == null || book.getAuthors().size() == 0) {
            throw new DataNotFoundException("List of authors is empty");
        }

        if (book.getGenres() == null || book.getGenres().size() == 0) {
            throw new DataNotFoundException("List of genres is empty");
        }

        book.setAuthors(bookDto.getAuthors());
        book.setGenres(bookDto.getGenres());

        return bookRepository.save(book).map(b -> bookMapper.toDto(b));
    }

    @Override
    @Transactional
    public Mono<Void> delete(String bookId) {
        Book book = bookRepository.findById(bookId).block();
        return bookRepository.deleteById(book.getId());
    }
}
