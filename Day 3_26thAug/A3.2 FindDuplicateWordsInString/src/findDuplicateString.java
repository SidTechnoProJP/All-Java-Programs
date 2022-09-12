import java.util.*;

public class findDuplicateString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nEnter a string: ");
        String stringInput = sc.nextLine();

        List<String> list = Arrays.asList(stringInput.split(" "));
        Set<String> duplicateWords = new HashSet<String>(list);

        boolean dupPresent = false;

        System.out.println("\nThe Duplicates are: ");
        for (String word : duplicateWords) {
            if (Collections.frequency(list, word) >= 2) {
                dupPresent = true;
                System.out.println(word);
            }
        }
        if(dupPresent == false){
            System.out.println("No Duplicates Found!");
        }

    }
}

//  big black cat in a big black hat

/*
---------------------------------
OUTPUT:
---------------------------------
(Case 1)
Enter a string:
big black cat in a big black hat

The Duplicates are:
big
black
---------------------------------
(Case 2)
Enter a string:
hello world

The Duplicates are:
No Duplicates Found!
---------------------------------
*/

