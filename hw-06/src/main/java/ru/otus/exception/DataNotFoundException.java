package ru.otus.exception;

import org.springframework.dao.DataAccessException;

public class DataNotFoundException extends DataAccessException {
    public DataNotFoundException(String e) {
        super(e);
    }
}
