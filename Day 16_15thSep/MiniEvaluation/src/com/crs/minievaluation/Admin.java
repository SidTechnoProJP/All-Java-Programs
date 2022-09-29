package com.crs.minievaluation;

import java.util.Scanner;

class Admin {
    static int courseCode, courseFees;
    static String courseName;
    Scanner sc = new Scanner(System.in);

    void courseGetData() {      //method to get course data input
        System.out.print("Enter the Course code,Course Name and Fees=");
        courseCode = sc.nextInt();
        courseName = sc.next();
        courseFees = sc.nextInt();
    }

    void showCourse() {         //method to show course details
        System.out.println(courseCode + "\t" + courseName + "\t" + courseFees);
    }
}

