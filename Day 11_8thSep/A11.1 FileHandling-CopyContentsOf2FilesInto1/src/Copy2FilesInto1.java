import java.io.*;
import java.util.Scanner;

public class Copy2FilesInto1 {
    public static void main(String[] args) throws IOException {

        String fileName_1, fileName_2, fileName_3;
        String file1_Content, file2_Content;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Name of the 1st File: ");
        fileName_1 = sc.nextLine();
        FileWriter fw= new FileWriter(fileName_1);
        PrintWriter op= new PrintWriter(fw);
        System.out.println("Enter your content in "+fileName_1+".txt: (To exit press e)");
        do {
            if ((file1_Content = sc.nextLine()).equals("e")) {
                op.close();
                break;

            } else {
                op.println(file1_Content);
                op.flush();
            }

        }while (true);

        System.out.println("Enter Name of the 2nd File: ");
        fileName_2 = sc.nextLine();
        FileWriter fw1= new FileWriter(fileName_2);
        PrintWriter op1= new PrintWriter(fw1);
        System.out.println("Enter your content in "+fileName_2+" .txt: (To exit press e)");
        do {
            if ((file2_Content = sc.nextLine()).equals("e")) {
                op1.close();
                break;

            } else {
                op1.println(file2_Content);
                op1.flush();
            }

        }while (true);

        System.out.println("Enter Name of the 3rd File in which you want to copy: ");
        fileName_3 = sc.nextLine();
        FileWriter fw3= new FileWriter(fileName_3);
        PrintWriter op3= new PrintWriter(fw3);
        FileReader fr1 = new FileReader(fileName_1);
        FileReader fr2 = new FileReader(fileName_2);
        int i;
        while (( i = fr1.read()) != -1){        // Print all the content of a file 1
            op3.print((char)i);
            op3.flush();
        }
        while (( i = fr2.read()) != -1){        // Print all the content of a file 2
            op3.print((char)i);
            op3.flush();
        }
        op3.close();
    }
}

/*
OUTPUT:
Enter Name of the 1st File:
a1
Enter your content in a1 .txt: (To exit press e)
hello
e
Enter Name of the 2nd File:
b1
Enter your content in b1 .txt: (To exit press e)
bye
bye bye
e
Enter Name of the 3rd File in which you want to copy:
c1

Process finished with exit code 0

(IN FILE c1)
hello
bye
bye bye
*/
