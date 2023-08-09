package ru.otus.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.User;
import ru.otus.service.OutputService;
import ru.otus.service.TestService;
import ru.otus.service.UserInitService;

@ShellComponent
public class ShellRunner {

    private final TestService testService;

    private final UserInitService userInitService;

    private final OutputService outputService;

    private User currentUser;

    public ShellRunner(TestService testService, UserInitService userInitService, OutputService outputService) {
        this.testService = testService;
        this.userInitService = userInitService;
        this.outputService = outputService;
    }

    @ShellMethod(value = "log in", key = {"l", "login"})
    public void login() {
        currentUser = userInitService.init();
        if (currentUser != null) {
            String[] args = {currentUser.getName()};
            outputService.showMessages("warning.info", args);
        }
    }

    @ShellMethod(value = "start test", key = {"s", "start"})
    public void startTest() {
        if (this.currentUser == null) {
            outputService.showMessages("warning.auth", null);
            return;
        }
        testService.startTest(this.currentUser);
    }

}

