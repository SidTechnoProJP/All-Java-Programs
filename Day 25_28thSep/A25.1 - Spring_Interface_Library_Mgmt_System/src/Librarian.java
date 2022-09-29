import java.util.ArrayList;
import java.util.List;

public class Librarian implements User , User2{

    List<Borrower> borrowerList = new ArrayList<>();

    public List<Borrower> getBorrowerList() {
        return borrowerList;
    }

    public void setBorrowerList(List<Borrower> borrowerList) {
        this.borrowerList = borrowerList;
    }

    @Override
    public String searchBook(List<String> booksList, String bookName) {
        for (String name:booksList){
            if (name.equalsIgnoreCase(bookName))
                return bookName + " is present in our Library";
        }
        return "Sorry, " + bookName + " is not present in our Library";
    }


    @Override
    public void loanHistory() {

    }

    @Override
    public void addBorrower() {

    }

    public String addBook(String addBookName) {


    }
}
