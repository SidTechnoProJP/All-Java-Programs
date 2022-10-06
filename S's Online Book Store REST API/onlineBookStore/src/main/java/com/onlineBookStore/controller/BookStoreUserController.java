package com.onlineBookStore.controller;

import com.onlineBookStore.model.Books;
import com.onlineBookStore.model.PurchaseHistory;
import com.onlineBookStore.model.UserAccount;
import com.onlineBookStore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookStoreUserController {
    @Autowired
    private IUserService userService;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @GetMapping("/books")
    public List<Books> findAll() {
        return jdbcTemplate.query("SELECT * FROM books", new BeanPropertyRowMapper<Books>(Books.class));
    }
    @PostMapping("/adduser")
    public String addUser(@RequestBody UserAccount user) {
        return userService.register(user)+" user registered successfully";
    }

    @PutMapping("/selectbooktobuy/{userId}")
    public String selectBuy(@PathVariable String userId, @RequestBody Books book) {
        return userService.addBooksToBuy(userId,book)+" Book(s) saved successfully";
    }

    @DeleteMapping("/deletebookfromcart/{userId}")
    public int deleteBookFromCart(@PathVariable String userId ,@RequestBody Books bookId){
        return userService.removeBookFromCart(userId,bookId);
    }

    @PostMapping("/addbooktocart/{userid}")
    public String addToCart(@PathVariable String userid, @RequestBody Books book) {
        return userService.addBookToCart(userid,book)+" Book(s) saved successfully";
    }
    @GetMapping("/buy/{userid}")
    public int buy(@PathVariable String userid){
        return userService.purchase(userid);
    }
    @GetMapping("/viewpurchasehistory/{userid}")
    public List<PurchaseHistory> viewHistroy(@PathVariable String userid){
        return userService.ViewPurchaseHistory(userid);
    }
}
