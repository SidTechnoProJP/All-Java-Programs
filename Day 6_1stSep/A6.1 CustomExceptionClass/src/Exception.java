import java.util.*;
class CustomException extends java.lang.Exception {
    CustomException(){
        System.out.println("Addition of 2 numbers cannot exceed 100!");
    }
}

public class Exception {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 2 numbers:");

        int input1 = sc.nextInt();
        int input2 = sc.nextInt();

        int sum = input1 + input2;

        if (sum>100){
            try {
                throw new CustomException();
            }
            catch (CustomException ce){
                System.out.println(ce);
            }
        }
        else {
            System.out.println("Addition is: "+sum);
        }
    }
}
