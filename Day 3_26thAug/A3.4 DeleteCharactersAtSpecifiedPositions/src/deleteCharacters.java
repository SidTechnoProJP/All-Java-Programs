import java.util.Scanner;

public class deleteCharacters {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter a string: ");
        String input = sc.nextLine();

        System.out.println("\nEnter the character that you want to delete: ");
        String delChar = sc.nextLine();

        //System.out.println("The string before removing character: " + input);
        input = input.replace(delChar, "");
        System.out.println("\nThe string after removing character: " + input);
    }
}
/*
--------------------------------------------
OUTPUT
--------------------------------------------
Enter a string:
hello

Enter the character that you want to delete:
e

The string after removing character: hllo
--------------------------------------------
*/