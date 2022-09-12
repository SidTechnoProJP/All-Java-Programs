import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MyBookShop {
    static Map<String, Books> books = new HashMap<>();
    public static void main(String[] args) {
        books.put("A",new Books(101, "Faster than Lightning", 699, 6));
        books.put("B",new Books(102, "Life of Pi", 749, 3));
        books.put("C",new Books(103, "The Fault in our Stars", 399, 10));
        books.put("D",new Books(104, "Dune", 899, 1));
        Scanner scan = new Scanner(System.in);
        boolean ch = true;
        while (ch) {
            System.out.println("Welcome to Boook store !!");
            int choice = scan.nextInt();
        }

    }

    public static Books getBook(String name) {
        return books.get(name);
    }

    public static boolean buyBook(String name, int numberOfBooks) {
        if (books.get(name).getQuantity() - numberOfBooks > 0) {
            books.get(name).setQuantity(numberOfBooks);
            return true;
        }
        return false;
    }

    public static void displayBookDetails(Books book) {
        System.out.println("Book Name :" + book.getBookName());
        System.out.println("Book Price :" + book.getPrice());
        System.out.println("Book left :" + book.getQuantity());
    }

    public static void displayBookNames() {
        books.forEach((name, book) -> System.out.println(name));
    }
}
