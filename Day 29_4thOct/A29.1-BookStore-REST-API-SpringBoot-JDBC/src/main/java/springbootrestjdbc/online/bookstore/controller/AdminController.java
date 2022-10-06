package springbootrestjdbc.online.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springbootrestjdbc.online.bookstore.dao.AdminDAO;
import springbootrestjdbc.online.bookstore.entity.Book;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminDAO adminDAO;

    @GetMapping("/showBooks")
    public List<Book> findAll() {
        return adminDAO.findAll();
    }

    @DeleteMapping("/deleteBook/{bookid}")
    public String deleteById(@PathVariable int id) {
        return adminDAO.deleteById(id)+" Book(s) deleted from the database";
    }

    @PostMapping("/addBook")
    public String save(@RequestBody Book b) {
        return adminDAO.save(b)+" Book(s) saved successfully";
    }

    /*@PutMapping("/employees/{id}")
    public String update(@RequestBody Book b, @PathVariable int id) {
        return adminDAO.update(b, id)+" Book(s) updated successfully";
    }*/

}
