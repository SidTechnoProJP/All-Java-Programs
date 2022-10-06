package com.onlineBookStore.service;

import com.onlineBookStore.model.Books;
import com.onlineBookStore.model.PurchaseHistory;
import com.onlineBookStore.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class UserService implements IUserService{
    @Autowired
    JdbcTemplate jdbcTemplate;



    @Override
    public int addBookToCart(String userId,Books book) {
        int result=0;
        List<Books> resultBook =jdbcTemplate.query("select * from books where BookID=?",new BeanPropertyRowMapper<Books>(Books.class),book.getBookID());
        int booksLeft =resultBook.get(0).getQUANTITY()-book.getQUANTITY();
        if(booksLeft>=0){
             jdbcTemplate.update("update Books set QUANTITY=? where BookID=?",booksLeft,book.getBookID());
        result= jdbcTemplate.update("INSERT INTO cart (UserId, BookId, quantity) VALUES (?, ?, ?)",userId,book.getBookID(),book.getQUANTITY());
        }
return  result;
    }

    @Override
    public int removeBookFromCart(String id, Books book) {

        return checkUser(id)? jdbcTemplate.update("DELETE FROM cart WHERE userId=? and BookId=", id,book.getBookID()):0;

    }

    @Override
    public int register(UserAccount userAccount) {
        return jdbcTemplate.update("INSERT INTO useraccounts (UserId, password,PhoneNumber) VALUES (?, ?, ?)",userAccount.getUserId(),userAccount.getPassword(),userAccount.getPhoneNumber());
    }


    @Override
    public int addBooksToBuy(String userId,Books book) {
        if(checkUser(userId)) {
            addBookToCart(userId, book);
            return purchase(userId);
        }
        return 0;
    }

    @Override
    public int purchase(String userId) {
        int a=0;
        if(checkUser(userId)) {
             a = jdbcTemplate.update("INSERT INTO purchase (UserId, BookId,quantity,purchaseDate) SELECT *,? FROM cart where UserId=?", LocalDate.now(), userId);
            clearCart(userId);
        }
        return a;
    }

    @Override
    public List<PurchaseHistory> ViewPurchaseHistory(String userId) {
        return checkUser(userId)?jdbcTemplate.query("select * from purchase",new BeanPropertyRowMapper<PurchaseHistory>(PurchaseHistory.class) ):null;
    }

    public void clearCart(String userId){
        if(checkUser(userId)) {
            jdbcTemplate.update("delete from cart where UserId=?", userId);
        }
    }

    boolean checkUser(String userId){
        List<UserAccount> user = jdbcTemplate.query("select * from userAccounts where UserId=? ",new BeanPropertyRowMapper<UserAccount>(UserAccount.class),userId);
        return  (user.size() != 0);
    }
}
