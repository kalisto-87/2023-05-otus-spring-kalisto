package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.User;

@Service
public class UserInitServiceImpl implements UserInitService {

    private InputService inputService;

    private OutputService outputService;

    public UserInitServiceImpl(InputService inputService, OutputService outputService) {
        this.inputService = inputService;
        this.outputService = outputService;
    }

    public User init() {
        outputService.showMessages("greeting", null);
        outputService.showMessages("user.surname", null);
        String surname = inputService.inputString();
        outputService.showMessages("user.name", null);
        String name = inputService.inputString();
        User user = new User(name, surname);
        return user;
    }
}
