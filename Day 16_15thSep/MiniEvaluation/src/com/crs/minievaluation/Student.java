package com.crs.minievaluation;

import java.util.Scanner;

class Student extends Admin {
    static String studentName, studentCourseName;
    static int studentId;

    Scanner sc = new Scanner(System.in);

    void studentGetData() {     //method to get input of students
        System.out.print("Enter Student ID ,Student Name , Course Name: ");
        studentId = sc.nextInt();
        studentName = sc.next();
        courseName = sc.next();
    }

    void studentPutData() {     //method to show student data
        System.out.println(studentId + "\t" + studentName + "\t" + studentCourseName);
    }
}
