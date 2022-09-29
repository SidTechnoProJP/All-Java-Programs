import java.util.List;

public interface User {
//common methods in all three classes
    String searchBook(List<String> booksList, String bookName);
    void loanHistory();
}
