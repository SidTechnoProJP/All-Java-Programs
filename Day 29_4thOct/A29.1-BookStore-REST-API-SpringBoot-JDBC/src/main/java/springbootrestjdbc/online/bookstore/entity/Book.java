package springbootrestjdbc.online.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int bookID;
    private String bookTitle;
    private int bookPrice;
    private int bookQty;
}
