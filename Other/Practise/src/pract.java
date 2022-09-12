import java.util.Scanner;

public class pract {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int n = sc.nextInt();
        System.out.println(Math.sqrt(n));


        if(n == Math.sqrt(n) == 0)
            System.out.println("true");
        else
            System.out.println("F");


        /*
        if (Math.sqrt(n)){
            System.out.println("Number "+n+" is a perfect sqrt");
        }
        else{
            System.out.println("Number "+n+" is NOT a perfect sqrt");
        }

         */

    }
}

















/*Random random = new Random();
        int x = random.nextInt(100);
        int y = random.nextInt(100);
        System.out.println("2 Random number between 0 and 100: " + x +" "+ y);

//        System.out.println("1st random number is: " + Math.random());
//        System.out.println("2nd random number is: " + Math.random());*/




    /*public static boolean isP(int no){
            if(no<=1){
                return false;
            }
            for (int i=2; i<Math.sqrt(no); i++){
                if (no%2==0){
                    return false;
                }
            }
            return true;
        }

    }*/

    /*Scanner s = new Scanner(System.in);

        System.out.println("Enter the first number:");
        int f = s.nextInt();

        System.out.println("Enter the second number:");
        int l = s.nextInt();

        System.out.println("The list of p nos between " + f + " and " + l + " are:");
        for (int i = f; i < l; i++) {
            if (isP(i)) {
                System.out.println(i);
            }*/

       /* for (int i=f; i<l; i++){
        if (isP(no)) {
            System.out.println(no + " is a PN");
        } else {
            System.out.println(no + " is not a PN");
        }
    }
    public static boolean isP(int no){
        if(no<=1){
            return false;
        }
        for (int i=2; i<Math.sqrt(no); i++){
            if (no%2==0){
                return false;
            }
        }
            return true;
    }
}*/



        /*System.out.println("Enter your name, age and salary: ");
        String name =  myObj.nextLine();
        int age = myObj.nextInt();
        double salary = myObj.nextDouble();

        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
        System.out.println("Salary: "+salary);
        //System.out.println(3+3);
*/
    //}
//}
