package com.onlineBookStore.service;

import com.onlineBookStore.model.Books;
import com.onlineBookStore.model.PurchaseHistory;
import com.onlineBookStore.model.UserAccount;

import java.util.List;

public interface IUserService  {
    int addBookToCart(String id,Books book);
    int removeBookFromCart(String id,Books book);
    int register(UserAccount userAccount);
    int addBooksToBuy(String id,Books books);
    int purchase(String userId);
    List<PurchaseHistory> ViewPurchaseHistory(String userId);
}
