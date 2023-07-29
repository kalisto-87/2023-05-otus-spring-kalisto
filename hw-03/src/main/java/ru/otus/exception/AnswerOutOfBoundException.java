package ru.otus.exception;

public class AnswerOutOfBoundException extends IndexOutOfBoundsException {
    public AnswerOutOfBoundException(String s) {
        super(s);
    }
}
