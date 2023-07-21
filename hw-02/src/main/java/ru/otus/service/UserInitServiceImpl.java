package ru.otus.service;

import lombok.AllArgsConstructor;
import ru.otus.domain.User;

@AllArgsConstructor
public class UserInitServiceImpl implements UserInitService {

    private InputService inputService;

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
