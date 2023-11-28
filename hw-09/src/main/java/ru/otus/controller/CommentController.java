package ru.otus.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.dto.BookDto;
import ru.otus.dto.CommentDto;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    private final BookService bookService;

    @GetMapping("/comment/list/{id}")
    public String commentList(@PathVariable long id, Model model) {
        BookDto book = bookService.findById(id);
        List<CommentDto> commentDtoList = commentService.findCommentsByBook(book);
        model.addAttribute("comments", commentDtoList);
        return "comment/list";
    }
}
