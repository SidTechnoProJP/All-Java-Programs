import java.util.*;

public class Account {  //declaring variables
    private int id = 0;
    private double balance = 0;
    private double annualInterestRate = 0.0;
    private Date dateCreated;
    private double withdrawAmt;
    private double depositAmt;

    public Account()
    {
        dateCreated = new Date();
    }

    public Account(int id, double balance, double annualInterestRate)
    {
        this();
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        dateCreated = new Date();
    }

    public int getId() {    //getter or accessor method for Id
        return id;
    }

    public void setId(int id) {     //setter or mutator method for Id
        this.id = id;
    }

    public double getBalance() {    //getter or accessor method for Balance
        return balance;
    }

    public void setBalance(double balance) {    //setter or mutator method for Balance
        this.balance = balance;
    }

    public double getAnnualInterestRate() {     //getter or accessor method for Interest Rate
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {      //setter or mutator method for Interest Rate
        this.annualInterestRate = annualInterestRate;
    }

    public String getDateCreated() {      //getter or accessor method for date
        return this.dateCreated.toString();
    }
    public double getMonthlyInterestRate() // method for Monthly Interest rate
    {
        return annualInterestRate/12;
    }
    public double getMonthlyInterest() // formula for Monthly Interest rate
    {
        return balance * getMonthlyInterestRate();
    }

    public double withdraw()    //method for withdrawing amount
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Withdrawal Amount: ");
        double withdrawAmt = sc.nextDouble();
        return balance = balance - withdrawAmt;
    }

    public double deposit()     //method for depositing amount
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Deposit Amount: ");
        double depositAmt = sc.nextDouble();
        return balance = balance + depositAmt;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Account[] accArray;
        accArray =  new Account[3];

        accArray[0] = new Account(101, 3000000, 0.15);
        accArray[1] = new Account(102, 1250000, 0.25);
        accArray[2] = new Account(103, 6300000, 0.05);

        while (true) {
            System.out.println("\n**********BANK MENU**********");
            System.out.println("\n1. Display Account Details\n2. Show Current Balance\n3. Withdraw amount\n4. Deposit\n5. Exit");
            System.out.println("\nEnter your choice: ");
            int ch = in.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("\nEnter your Id: ");
                    int id = in.nextInt();
                    int originalid = id;
                    for (int i = 0; i < 3; i++) {
                        if (accArray[i].getId() == id) {
                            id = i;
                            break;
                        }
                    }
                    if (originalid == id) {
                        System.out.println("Invalid Id!");
                        return;
                    }
                    System.out.println("\n**********ACCOUNT DETAILS**********");
                    System.out.println("\nID: " + accArray[id].getId());
                    System.out.println("\nBalance: " + accArray[id].getBalance());
                    System.out.println("\nAnnual Interest rate: " + accArray[id].annualInterestRate);
                    System.out.println("\nDate Created on: "+accArray[id].getDateCreated());
                    break;

                case 2:
                    System.out.println("\nEnter your Id: ");
                    int id1 = in.nextInt();
                    int originalid1 = id1;
                    for (int i = 0; i < 3; i++) {
                        if (accArray[i].getId() == id1) {
                            id1 = i;
                            break;
                        }
                    }
                    if (originalid1 == id1) {
                        System.out.println("Invalid Id!");
                        return;
                    }
                    System.out.println("\nYour Current Balance: " + accArray[id1].getBalance());
                    break;

                case 3:
                    System.out.println("\nEnter your Id: ");
                    int id2 = in.nextInt();
                    int originalid2 = id2;
                    for (int i = 0; i < 3; i++) {
                        if (accArray[i].getId() == id2) {
                            id2 = i;
                            break;
                        }
                    }
                    if (originalid2 == id2) {
                        System.out.println("Invalid Id!");
                        return;
                    }
                    System.out.println("Current balance after withdrawal is: " + accArray[id2].withdraw());
                    break;

                case 4:
                    System.out.println("\nEnter your Id: ");
                    int id3 = in.nextInt();
                    int originalid3 = id3;
                    for (int i = 0; i < 3; i++) {
                        if (accArray[i].getId() == id3) {
                            id3 = i;
                            break;
                        }
                    }
                    if (originalid3 == id3) {
                        System.out.println("Invalid Id!");
                        return;
                    }
                    System.out.println("Current balance after deposit is: " + accArray[id3].deposit());
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}



