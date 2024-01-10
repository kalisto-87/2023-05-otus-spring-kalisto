package ru.otus.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.security.SecurityConfig;
import ru.otus.service.GenreService;

@WebMvcTest(GenreController.class)
@Import(SecurityConfig.class)
public class GenreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    GenreService genreService;

    @Test
    public void shouldAccess() throws Exception {

    }
}
