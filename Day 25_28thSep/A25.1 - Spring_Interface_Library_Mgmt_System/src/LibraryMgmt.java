import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LibraryMgmt {
    static Scanner scanner = new Scanner(System.in);
    static Librarian librarian = new Librarian();
    static Clerk clerk = new Clerk();
    static Borrower borrower = new Borrower();

    static Map<Integer, Borrower> borrowerMap;

    static List<String> bookList = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("***WELCOME TO LIBRARY MANAGEMENT SYSTEM***");
            System.out.println("1. Librarian\n2. Clerk\n3. Borrower\n4. Exit");
            System.out.println("Enter your choice : ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> librarianLogin();
                case 2 -> clerkLogin();
                case 3 -> borrowerLogin();
                case 4 -> {
                    return;
                }
            }
        }
    }

    private static void librarianLogin() {
        while (true) {
            System.out.println("***LIBRARIAN MENU***");
            System.out.println("1. Search Book by Title");
            System.out.println("2. View Loan History");
            System.out.println("3. Add Borrower");
            System.out.println("4. Add New Book");
            System.out.println("5. Delete a Book");
            System.out.println("6. Exit");
            System.out.println("Enter your choice : ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> librarian.searchBook)();
                case 2 -> librarian.addBorrower();
                case 3 -> librarian.loanHistory();
                case 4 -> librarian.addBook();
                case 5 -> librarian.deleteBook();
                case 6 -> {
                    return;
                }

            }
        }
    }

    private static void clerkLogin() {
        while (true) {
            System.out.println("***CLERK MENU***");
            System.out.println("1. Search Book by Title");
            System.out.println("2. View Loan History");
            System.out.println("3. Add Borrower");
            System.out.println("4. View Pending Fines");
            System.out.println("5. Exit");
            System.out.println("Enter your choice : ");
            int choice = scanner.nextInt();
            switch (choice) {
                //case 1 -> clerk.searchBook();
                case 2 -> clerk.addBorrower();
                case 3 -> clerk.loanHistory();
                //case 4 -> clerk.showPendingFines();
                case 5 -> {
                    return;
                }

            }
        }
    }

    private static void borrowerLogin() {
        while (true) {
            System.out.println("***BORROWER MENU***");
            System.out.println("1. Search Book by Title");
            System.out.println("2. View Loan History");
            System.out.println("3. Loan a Book");
            System.out.println("4. Exit");
            System.out.println("Enter your choice : ");
            int choice = scanner.nextInt();
            switch (choice) {
                // case 1 -> borrower.searchBook();
                case 2 -> borrower.loanHistory();
                //case 3 -> borrower.loanBook();
                case 4 -> {
                    return;
                }
            }
        }
    }
}

