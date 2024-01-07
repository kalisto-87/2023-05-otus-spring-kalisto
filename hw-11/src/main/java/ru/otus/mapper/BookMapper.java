package ru.otus.mapper;

import org.mapstruct.Mapper;
import ru.otus.domain.Book;
import ru.otus.dto.BookDto;

import java.util.List;

@Mapper(config = MappingConfig.class, uses = {
        AuthorMapper.class,
        GenreMapper.class,
        CommentMapper.class
})
public interface BookMapper {

    BookDto toDto(Book book);

    List<BookDto> toDtoList(List<Book> books);

    Book toDomain(BookDto bookDto);
}
