package ru.otus.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.domain.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Проверка")
@SpringBootTest(properties = "spring.=")
public class UserInitServiceImplTest {

    private UserInitServiceImpl userInitServiceImpl;

    @MockBean
    private InputService inputService;

    @MockBean
    private OutputService outputService;

    @Test
    @DisplayName("процедуры инициализации пользовтеля")
    public void checkGetUser() {
        userInitServiceImpl = new UserInitServiceImpl(inputService, outputService);
        String inputName = "Noname";
        when(inputService.inputString()).thenReturn(inputName);
        User user = userInitServiceImpl.init();
        assertEquals(inputName, user.getName());
        assertEquals(inputName, user.getSurname());
    }

}
