package com.redis.example.repository;

import com.redis.example.model.Books;

import java.util.List;

public interface BooksRepository {

    void save(final Books books);

    List<Books> findAll();

    Books findById(final String id);

    void update(final Books books);

    void delete(final Integer id);


}
