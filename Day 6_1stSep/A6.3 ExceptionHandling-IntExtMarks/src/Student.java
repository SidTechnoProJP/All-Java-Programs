/*
Write a program for user defined exception that checks the internal
and external marks; if the internal marks is greater than 40 it raises
the exception "Internal marks is exceeded"; if the external marks is
greater than 60 it raises the exception and displays the message
"The External Marks is exceeded". Create the above exception and use it in your program.
* */


import java.util.InputMismatchException;
import java.util.Scanner;

class InternalException extends Exception{
    InternalException(){
        super("Internal marks have been exceeded!");
    }
}

class ExternalException extends Exception{
    ExternalException(){
        super("External marks have been exceeded!");
    }
}

public class Student {
    public int rollNo, internalMarks, externalMarks, total;
    public String name;
    Student(int rollNo, String name, int internalMarks, int externalMarks){
        this.rollNo = rollNo;
        this.name = name;
        this.internalMarks = internalMarks;
        this.externalMarks = externalMarks;
    }
    void showData(int n){
        total = internalMarks + externalMarks;
        if(internalMarks<=40 && externalMarks<=60) {
            System.out.println("\n**********STUDENT DATA**********");
            System.out.println("STUDENT " + n + ":");
            System.out.println("Roll No: " + rollNo);
            System.out.println("Name: " + name);
            System.out.println("Internal Marks: " + internalMarks);
            System.out.println("External Marks: " + externalMarks);
            System.out.println("Total Marks: "+total);
        }
        else {
            return;
        }
    }
}


class Examination{
    static int rollNo, internalMarks,externalMarks, total;
    static String name;
    static Student getData () throws InternalException, ExternalException {
        Scanner sc = new Scanner(System.in);
        Scanner string = new Scanner(System.in);

        try {
            System.out.println("Enter the Roll number: ");
            rollNo = sc.nextInt();
            System.out.println("Enter Name: ");
            name = string.nextLine();
            System.out.println("Enter Internal Marks: ");
            internalMarks = sc.nextInt();
            if (internalMarks > 40) {
                throw new InternalException();
            }
            System.out.println("Enter External Marks: ");
            externalMarks = sc.nextInt();
            if (externalMarks > 60) {
                throw new ExternalException();
            }
        }

        catch (InternalException ie){
            System.out.println(ie);
        }

        catch (ExternalException ee){
            System.out.println(ee);
        }

        catch (InputMismatchException me){
            System.out.println("Wrong Input Type!");
        }

        return new Student(rollNo, name, internalMarks, externalMarks);
    }

    public static void main(String[] args) throws ExternalException, InternalException {
        Scanner sc = new Scanner(System.in);
        Student[] arr;

        System.out.println("Specify the desired number of students: ");
        int n = sc.nextInt();

        arr = new Student[n];

        for (int i=0; i< n; i++) {
            System.out.println("\n********ENTER STUDENT DATA********");
            System.out.println("For Student "+(i+1)+":");
            arr[i] = getData();
        }

        for (int i=0; i< n; i++) {
            arr[i].showData(i+1);
        }
    }
}

/*
(CASE 1)
Specify the desired number of students:
2

********ENTER STUDENT DATA********
For Student 1:
Enter the Roll number:
1
Enter Name:
AAA
Enter Internal Marks:
39
Enter External Marks:
59

********ENTER STUDENT DATA********
For Student 2:
Enter the Roll number:
2
Enter Name:
BBB
Enter Internal Marks:
20
Enter External Marks:
30

**********STUDENT DATA**********
STUDENT 1:
Roll No: 1
Name: AAA
Internal Marks: 39
External Marks: 59
Total Marks: 98

**********STUDENT DATA**********
STUDENT 2:
Roll No: 2
Name: BBB
Internal Marks: 20
External Marks: 30
Total Marks: 50

Process finished with exit code 0

(CASE 2)


* */