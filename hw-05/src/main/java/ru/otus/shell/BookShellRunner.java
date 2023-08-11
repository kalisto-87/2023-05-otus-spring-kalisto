package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import ru.otus.service.BookService;

@ShellComponent
@RequiredArgsConstructor
public class BookShellRunner extends AbstractShellComponent {

    private final BookService bookService;
}
