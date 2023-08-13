package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookDao;

@Service
@RequiredArgsConstructor
public class BookServicesImpl implements BookService {

    private final BookDao dao;

}
