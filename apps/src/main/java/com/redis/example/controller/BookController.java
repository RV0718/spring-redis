package com.redis.example.controller;

import com.redis.example.model.Books;
import com.redis.example.repository.BooksRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.redis.example.constants.ApplicationConstants.ALL_EP;
import static com.redis.example.constants.ApplicationConstants.API_BOOK;
import static com.redis.example.constants.ApplicationConstants.DELETE_EP;
import static com.redis.example.constants.ApplicationConstants.SAVE_EP;

@RestController
@RequestMapping(API_BOOK)
public class BookController {

    private final static Logger LOG = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BooksRepository booksRepository;

    @PostMapping(SAVE_EP)
    public String saveBooks(@RequestBody Books books) {
        try {
            booksRepository.save(books);
        } catch (Exception ex) {
            LOG.error("Exception caught while saving books {} {}", ex, books);
            return "Error while saving Books";
        }
        return "Books Saved Successfully";
    }

    @GetMapping(ALL_EP)
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }


    @DeleteMapping(DELETE_EP + "/{id}")
    public String deleteUserById(@PathVariable("id") Integer id) {
        try {
            booksRepository.delete(id);
        } catch (Exception ex) {
            LOG.error("Exception caught while deleting books by id : {} {}", id, ex);
            return "Error while deleting book";
        }
        return "Books Deleted Successfully";
    }
}

