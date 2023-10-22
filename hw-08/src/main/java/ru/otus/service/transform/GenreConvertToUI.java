package ru.otus.service.transform;

public interface GenreConvertToUI {

    String findAll();

    String findByName(String genreName);

    String insert(String genreName);

    String update(String genreId, String genreName);

    String delete(String genreId);
}
