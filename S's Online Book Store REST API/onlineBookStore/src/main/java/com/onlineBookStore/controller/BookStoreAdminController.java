package com.onlineBookStore.controller;

import com.onlineBookStore.model.Books;
import com.onlineBookStore.model.UserAccount;
import com.onlineBookStore.service.IbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class BookStoreAdminController {
    @Autowired
    private IbookService bookService;

@Autowired
JdbcTemplate jdbcTemplate;
    @GetMapping("/book")
    public List<Books> find() {
        return jdbcTemplate.query("SELECT * FROM books", new BeanPropertyRowMapper<Books>(Books.class));
    }

    @PostMapping("/addbook")
    public String saveBook(@RequestBody Books book) {
        return bookService.addBook(book)+" Book(s) saved successfully";
    }

    @PutMapping("/changebookprice")
    public String changeBookPrice(@RequestBody List<String> values) {
        return bookService.changeAmount(values.get(0),Float.parseFloat(values.get(1)))+" Book(s) saved successfully";
    }

    @DeleteMapping("/deletebook")
    public int deleteBook(@RequestBody String bookId){
        return bookService.RemoveBook(bookId);
    }


}
