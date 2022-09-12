import java.util.stream.IntStream;

public class Student {
    String[] studentName = new String[]{"Sid", "John", "Max"};
    int[] rollNo = new int[]{101, 202, 303};

    void showData(){
        System.out.println("\n******STUDENT DATA*****");
        System.out.println("\nStudent 1");
        System.out.println("Name : " + studentName[0]);
        System.out.println("Roll No : " + rollNo[0]);
        System.out.println("\nStudent 2");
        System.out.println("Name : " + studentName[1]);
        System.out.println("Roll No : " + rollNo[1]);
        System.out.println("\nStudent 3");
        System.out.println("Name : " + studentName[2]);
        System.out.println("Roll No : " + rollNo[2]);
    }
}

class Test extends Student{
    String[] subjectName = new String[]{"DSA", "TOC", "SEPM"};
    int[] marksSid = new int[]{65, 76, 35};
    int[] marksJohn = new int[]{56, 88, 54};
    int[] marksMax = new int[]{98, 89, 97};

    void show_marks(){
        System.out.println("\n*****STUDENT MARKS*****");
        System.out.println("\nStudent 1");
        System.out.println("Name: " + studentName[0]);
        System.out.println("Subject Name and Marks: \n(1) "+subjectName[0]+":"+marksSid[0]+
                " (2) "+subjectName[1]+":"+marksSid[1]+" (3) "+subjectName[2]+":"+marksSid[0]);
        System.out.println("\nStudent 2");
        System.out.println("Name: " + studentName[1]);
        System.out.println("Subject Name: \n(1) "+subjectName[0]+":"+marksJohn[0]+
                " (2) "+subjectName[1]+":"+marksJohn[1]+" (3) "+subjectName[2]+":"+marksJohn[0]);
        System.out.println("\nStudent 3");
        System.out.println("Name: " + studentName[2]);
        System.out.println("Subject Name: \n(1) "+subjectName[0]+":"+marksMax[0]+
                " (2) "+subjectName[1]+":"+marksMax[1]+" (3) "+subjectName[2]+":"+marksMax[0]);
    }
}

interface Sports{
    int[] SPORTS_MARKS = new int[]{195, 166, 90};
    void show_sportswt();
}

class Result extends Test implements Sports{
    void total_marks(){

        int sumSid = IntStream.of(marksSid).sum();
        int totalSid = sumSid + SPORTS_MARKS[0];

        int sumJohn = IntStream.of(marksJohn).sum();
        int totalJohn = sumJohn + SPORTS_MARKS[1];

        int sumMax = IntStream.of(marksMax).sum();
        int totalMax = sumMax + SPORTS_MARKS[2];

        System.out.println("\n*****TOTAL MARKS*****");
        System.out.println("Total Marks of " + studentName[0] + ": " + totalSid + "/500");
        System.out.println("Total Marks of " + studentName[1] + ": " + totalJohn + "/500");
        System.out.println("Total Marks of " + studentName[2] + ": " + totalMax + "/500");
    }
    public void show_sportswt() {
        System.out.println("\n*****SPORTS MARKS*****");
        System.out.println(studentName[0] + ": " + SPORTS_MARKS[0]);
        System.out.println(studentName[1] + ": " + SPORTS_MARKS[1]);
        System.out.println(studentName[2] + ": " + SPORTS_MARKS[2]);
    }
}

class Main {
    public static void main(String[] args) {
        Student s = new Student();
        s.showData();
        Test t = new Test();
        t.show_marks();
        Result r = new Result();
        r.show_sportswt();
        r.total_marks();
    }
}

/*
OUTPUT:

******STUDENT DATA*****

Student 1
Name : Sid
Roll No : 101

Student 2
Name : John
Roll No : 202

Student 3
Name : Max
Roll No : 303

*****STUDENT MARKS*****

Student 1
Name: Sid
Subject Name and Marks:
(1) DSA:65 (2) TOC:76 (3) SEPM:65

Student 2
Name: John
Subject Name:
(1) DSA:56 (2) TOC:88 (3) SEPM:56

Student 3
Name: Max
Subject Name:
(1) DSA:98 (2) TOC:89 (3) SEPM:98

*****SPORTS MARKS*****
Sid: 195
John: 166
Max: 90

*****TOTAL MARKS*****
Total Marks of Sid: 371/500
Total Marks of John: 364/500
Total Marks of Max: 374/500

* */

