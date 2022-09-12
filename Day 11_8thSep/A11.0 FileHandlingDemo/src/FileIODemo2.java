import java.io.*;

public class FileIODemo2 {
    public static void main(String[] args) throws IOException {
        FileWriter fw =new FileWriter("c.txt",true);
        PrintWriter output1 =new PrintWriter(fw);
//        PrintWriter writer = new PrintWriter("c.txt");
//        writer.print("");
//        writer.close();

        output1.println("This is C:");
        output1.println("Luke this is your Father!");
        output1.close();
    }
}
