package com.onlineBookStore.service;

import com.onlineBookStore.model.Books;

import java.util.List;

public interface IbookService {
    int addBook(Books book);
    int RemoveBook(String bookId);
    int changeAmount(String bookId,float price);

}
