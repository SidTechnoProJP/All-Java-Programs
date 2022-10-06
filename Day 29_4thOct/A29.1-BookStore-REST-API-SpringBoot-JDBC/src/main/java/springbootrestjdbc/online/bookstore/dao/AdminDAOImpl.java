package springbootrestjdbc.online.bookstore.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import springbootrestjdbc.online.bookstore.entity.Book;

import java.util.List;

@Repository
public class AdminDAOImpl implements AdminDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM tbl_book", new BeanPropertyRowMapper<Book>(Book.class));
    }

    /*@Override
    public Book findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM tbl_book WHERE bookid=?", new BeanPropertyRowMapper<Book>(Book.class), id);
    }*/

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM tbl_book WHERE bookid=?", id);
    }

    @Override
    public int save(Book e) {
        return jdbcTemplate.update("INSERT INTO tbl_book (bookid, booktitle, bookprice, bookqty) VALUES (?, ?, ?, ?)",
                e.getBookID(),e.getBookTitle(),e.getBookPrice(),e.getBookQty());
    }

    @Override
    public int update(int changeAmt, int id) {
        return jdbcTemplate.update("UPDATE tbl_book SET  bookprice = ? WHERE bookid = ?", changeAmt, id);
    }
}
