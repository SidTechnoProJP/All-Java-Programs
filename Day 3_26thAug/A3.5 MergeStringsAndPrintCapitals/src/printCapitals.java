import java.util.Scanner;

public class printCapitals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter the 1st string: ");
        String s1 = sc.nextLine();

        System.out.println("\nEnter the 2nd string: ");
        String s2 = sc.nextLine();

        System.out.println("\nDisplaying all UpperCase letters after merging: ");
        String merge = s1 + s2;
        for(int i = 0; i < merge.length(); i++) {
            if(Character.isUpperCase(merge.charAt(i))) {
                System.out.print(merge.charAt(i));
            }
        }
    }
}
/*
OUTPUT:
Enter the 1st string:
ILikeC

Enter the 2nd string:
MaryLikesPython

Displaying all UpperCase letters after merging:
ILCMLP
**/