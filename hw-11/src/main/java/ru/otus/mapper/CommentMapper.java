package ru.otus.mapper;

import org.mapstruct.Mapper;
import ru.otus.domain.Comment;
import ru.otus.dto.CommentDto;

import java.util.List;

@Mapper(config = MappingConfig.class)
public interface CommentMapper {

    CommentDto toDto(Comment comment);

    List<CommentDto> toDtoList(List<Comment> comments);

    Comment toDomain(CommentDto commentDto);

}
