package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.User;

@Service
public class UserInitServiceImpl implements UserInitService {

    private InputService inputService;

    public UserInitServiceImpl(InputService inputService) {
        this.inputService = inputService;
    }

    public User init() {
        System.out.println("Hello, Dear user");
        System.out.println("Please, Input your surname:");
        String surname = inputService.inputString();
        System.out.println("Please, Input your name:");
        String name = inputService.inputString();
        User user = new User(name, surname);
        return user;
    }
}
