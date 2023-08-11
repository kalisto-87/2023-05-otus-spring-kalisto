package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import ru.otus.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
public class GenreShellRunner extends AbstractShellComponent {

    private final GenreService genreService;
}
