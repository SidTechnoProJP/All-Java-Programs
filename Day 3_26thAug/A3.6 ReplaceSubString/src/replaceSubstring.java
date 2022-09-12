import java.util.Scanner;

public class replaceSubstring {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter String: ");
        String str1 = sc.nextLine();

        System.out.println("\nEnter SubString: ");
        String str2 = sc.nextLine();

        int result = str1.indexOf(str2);
        if(result == -1) {
            System.out.println("Substring \""+ str2 + "\" not is present in the string.");
        }
        else {
            System.out.println("Substring \""+ str2 + "\" is present in the string.");
        }

        System.out.println("\nReplace substring \"" + str2 + "\" with:");
        String chSubstring = sc.nextLine();

        //System.out.println("The string before removing character: " + input);
        str1 = str1.replace(str2, chSubstring);
        System.out.println("\nString after modification: " + str1);
    }
}
