package ru.otus.service;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Scanner;

@Service
public class ConsoleInput implements InputService {

    private final InputStream inputStream = System.in;

    @Override
    public Integer inputInt() {
        Scanner scanner = new Scanner(inputStream);
        return scanner.nextInt();
    }

    @Override
    public String inputString() {
        Scanner scanner = new Scanner(inputStream);
        return scanner.nextLine();
    }
}
