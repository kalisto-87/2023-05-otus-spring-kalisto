package ru.otus.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.config.AppProps;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Проверка вывода данных из файлов")
@SpringBootTest(properties = "spring.=")
public class ConsoleOutputTest {

    private ConsoleOutput consoleOutput;

    @MockBean
    private AppProps appProps;

    private ReloadableResourceBundleMessageSource messageSource;

    private Locale locale;

    @BeforeEach
    private void setUp() {
        messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setFallbackToSystemLocale(true);
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setBasenames("/i18n/appmessages");
        consoleOutput = new ConsoleOutput(appProps, messageSource);
    }

    @Test
    @DisplayName("На английском")
    public void checkShowMessageEn() {
        when(appProps.getLocale()).thenReturn(new Locale("en"));
        assertEquals("Hello, Dear user",
                messageSource.getMessage("greeting", null, appProps.getLocale()));
    }

    @Test
    @DisplayName("На немецком")
    public void checkShowMessageDe() {
        when(appProps.getLocale()).thenReturn(new Locale("de_DE"));
        assertEquals("Hallo, lieber Benutzer",
                messageSource.getMessage("greeting", null, appProps.getLocale()));
    }
}
