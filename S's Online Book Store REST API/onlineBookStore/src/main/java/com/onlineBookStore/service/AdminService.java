package com.onlineBookStore.service;

import com.onlineBookStore.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class AdminService implements IbookService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int addBook(Books book) {

        return jdbcTemplate.update("INSERT INTO Books  VALUES (?, ?, ?,?,?)", book.getBookID(),book.getBookName(),book.getQUANTITY(),book.getBookPrice(),book.getBookAuthor());

    }

    @Override
    public int RemoveBook(String bookId) {
        return jdbcTemplate.update("delete from  Books   where BookID= ?",bookId);
    }

    @Override
    public int changeAmount(String bookId, float price) {
        return jdbcTemplate.update("update  Books  set BookPrice=? where BookID= ?",price,bookId);

    }

}
