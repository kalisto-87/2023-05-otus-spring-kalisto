package ru.otus.service.transfer;

import java.util.List;

public interface BookConvertToUI {

    String findAll();

    String findByName(String bookName);

    String insert(String bookName, List<Long> authors, List<Long> genres);

    String update(long bookId, String bookName);

    String delete(long bookId);
}
