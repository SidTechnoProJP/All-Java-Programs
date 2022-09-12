import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileIODemo3 {
    public static void main(String[] args) throws IOException {
        String fname;
        int marks;
        Scanner ip=new Scanner(System.in);
        System.out.println("Enter a file name");
        fname= ip.nextLine();
        FileWriter fw= new FileWriter(fname);
        PrintWriter op= new PrintWriter(fw);
        System.out.println("Enter the 10 marks");
        op.println("The marks are: ");
        for(int i=0;i<10; i++)
        {
            marks= ip.nextInt();
            op.print(marks);
            op.flush();
        }
        op.close();
    }
}
