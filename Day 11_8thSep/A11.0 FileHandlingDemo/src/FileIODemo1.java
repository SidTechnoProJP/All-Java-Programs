import java.io.*;

public class FileIODemo1 {
    public static void main(String[] args) throws IOException  {
            File s = new File("a.txt");
            File s1 = new File("b.txt");
            PrintWriter output =new PrintWriter(s);
            PrintWriter output1 =new PrintWriter(s1);
            output1.println("This is B:\nAnakin!");
            output1.close();
            output.println("This is A:");
            output.println ("Hello There!");
            output.println("I have the higher ground");
            output.close();
    }
}
