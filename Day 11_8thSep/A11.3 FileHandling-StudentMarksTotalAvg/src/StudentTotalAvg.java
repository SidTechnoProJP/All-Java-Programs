import java.io.*;
import java.util.Scanner;

public class StudentTotalAvg {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter File Name for Marks: ");
        String fileName_1 = sc.nextLine();
        FileWriter fw = new FileWriter(fileName_1+".txt");
        PrintWriter op = new PrintWriter(fw);

        System.out.println("\nFor how many students? ");
        int studentNo = sc.nextInt();
        int[][] marks = new int[studentNo][6];
        System.out.println("\nEnter Marks for " + studentNo + " Students for" +
                " 6 Courses in " + fileName_1 + ".txt: ");
        for (int i = 0; i < studentNo; i++) {
            for (int j = 0; j < 6; j++) {
                marks[i][j] = sc.nextInt();
                op.print(marks[i][j] + " ");
            }
            op.println();
        }
        op.close();

        BufferedReader reader =new BufferedReader(new FileReader(fileName_1+".txt"));

        String Int_line;
        int counter=0;

        while ((Int_line = reader.readLine()) != null) {
            float total = 0;
            counter++;
            String [] marksarr = Int_line.split(" ");
            for (String s: marksarr){
                total += Integer.parseInt(s);
            }
            float avg = total/6;
            System.out.println("\nTotal Marks of Student "+counter+": "+total);
            System.out.println("Average of Student "+counter+": "+avg);
            //System.out.println();
        }
    }
}


/*
OUTPUT:
Enter File Name for Marks:
marks

For how many students?
3

Enter Marks for 3 Students for 6 Courses in marks.txt:
15 12 13 16 12 19
13 14 17 18 21 22
12 11 16 19 15 20

Total Marks of Student 1: 87.0
Average of Student 1: 14.5

Total Marks of Student 2: 105.0
Average of Student 2: 17.5

Total Marks of Student 3: 93.0
Average of Student 3: 15.5

* */





//        File file = new File("C:\\Users\\Siddhesh U S\\Desktop\\Assignments\\Day 11_8thSep\\A11.3 FileHandling-StudentMarksTotalAvg\\"+ fileName_1+".txt");
//        FileInputStream fileInputStream = new FileInputStream(file);
//        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);