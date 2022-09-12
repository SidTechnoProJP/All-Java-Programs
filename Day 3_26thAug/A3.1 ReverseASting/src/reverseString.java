import java.util.Scanner;

public class reverseString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder stringSB = new StringBuilder();

        System.out.println("\nEnter a String: ");
        String stringInput = sc.nextLine();

        stringSB.append(stringInput);
        stringSB.reverse();

        System.out.println("\nThe Reversed String is: \n"+stringSB);
    }
}

/*
The java best practice, in that case, would be using “StringBuilder” or “StringBuffer”.
These built-in functions modify a String without creating intermediate String objects
saving processing time and unnecessary memory usage.

OUTPUT
Enter a String:
abcdefghijklmnopqrstuvwxyz

The Reversed String is: 
zyxwvutsrqponmlkjihgfedcba

 */