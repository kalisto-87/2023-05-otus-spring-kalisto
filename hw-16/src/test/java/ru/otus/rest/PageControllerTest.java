package ru.otus.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.page.PageController;
import ru.otus.security.SecurityConfig;
import ru.otus.service.AuthorService;
import ru.otus.service.BookService;
import ru.otus.service.CommentService;
import ru.otus.service.GenreService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(PageController.class)
@Import(SecurityConfig.class)
public class PageControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    GenreService genreService;

    @MockBean
    BookService bookService;

    @MockBean
    CommentService commentService;

    @MockBean
    AuthorService authorService;

    @Test
    public void shouldAccessAnonymous() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void shouldAccessForUser() throws Exception {
        mvc.perform(get("/genre").with(user("user").roles("USER")))
                .andExpect(status().isOk());
        mvc.perform(get("/author").with(user("user").roles("USER")))
                .andExpect(status().isOk());
        mvc.perform(get("/genre").with(user("user").roles("USER")))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void shouldAccessForAdmin() throws Exception {
        mvc.perform(get("/addAuthor").with(csrf()))
                .andExpect(status().isOk());
        mvc.perform(get("/addGenre").with(csrf()))
                .andExpect(status().isOk());
    }

}
