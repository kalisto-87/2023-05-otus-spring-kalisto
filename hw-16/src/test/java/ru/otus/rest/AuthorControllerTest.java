package ru.otus.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.dto.AuthorDto;
import ru.otus.security.SecurityConfig;
import ru.otus.service.AuthorService;
import ru.otus.service.AuthorServiceImpl;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
@Import({AuthorServiceImpl.class, SecurityConfig.class})
public class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;

    private AuthorDto authorDto;

    @BeforeEach
    void setUp() {
        authorDto = new AuthorDto(10, "New Author");
    }

    @Test
    public void shouldAccessAnonymous() throws Exception {
        mvc.perform(get("/api/author")).andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    public void shouldAccessForUser() throws Exception {
        mvc.perform(get("/api/author").with(csrf())).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void shouldAccessForAdmin() throws Exception {
        mvc.perform(post("/api/author", authorDto).with(csrf()))
                .andExpect(status().is3xxRedirection());
    }
}
