import java.util.Scanner;

public class findSubSting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nEnter String: ");
        String str1 = sc.nextLine();
        System.out.println("\nEnter SubString: ");
        String str2 = sc.nextLine();

        int result = str1.indexOf(str2);
        if(result == -1) {
            System.out.println("Substring "+ str2 + " not is present in the string.");
        }
        else {
            System.out.println("Substring "+ str2 + " is present in the string.");
        }
    }
}

/*
-------------------------------------------
Output:
-------------------------------------------
(Case 1)
Enter String:
helloworld

Enter SubString:
xyz
Substring xyz not is present in the string.
-------------------------------------------
(Case 2)
Enter String:
helloworld

Enter SubString:
hello
Substring hello is present in the string.
-------------------------------------------
*/
