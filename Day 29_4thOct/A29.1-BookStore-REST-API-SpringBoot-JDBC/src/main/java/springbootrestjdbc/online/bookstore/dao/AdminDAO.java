package springbootrestjdbc.online.bookstore.dao;

import springbootrestjdbc.online.bookstore.entity.Book;

import java.util.List;

public interface AdminDAO {
    public List<Book> findAll();

//    public Book findById(int id);

    public int deleteById(int id);

    public int save(Book b);

    int update(int changeAmt, int id);
}
