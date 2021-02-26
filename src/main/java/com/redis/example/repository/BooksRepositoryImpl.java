package com.redis.example.repository;

import com.redis.example.model.Books;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class BooksRepositoryImpl implements BooksRepository {

    private static final String KEY = "Book";
    private RedisTemplate<Integer, Object> redisTemplate;
    private HashOperations hashOperations; //to access Redis cache


    public BooksRepositoryImpl(final RedisTemplate<Integer, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public void save(final Books book) {
        hashOperations.put(KEY, book.getId(), book);
    }

    @Override
    public List<Books> findAll() {
        List<Books> rlist = new LinkedList<>();
        hashOperations.entries(KEY).forEach((k, v) -> {
            rlist.add((Books) v);
        });
        return rlist;
    }

    @Override
    public Books findById(final String id) {
        return (Books) hashOperations.get(KEY, id);
    }

    @Override
    public void update(final Books books) {
        save(books);
    }

    @Override
    public void delete(final Integer id) {
        hashOperations.delete(KEY, id);
    }
}
