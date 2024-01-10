package ru.otus.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.security.SecurityConfig;
import ru.otus.service.AuthorService;

@WebMvcTest(AuthorController.class)
@Import(SecurityConfig.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    AuthorService authorService;
}
