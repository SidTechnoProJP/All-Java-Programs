package com.srs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SRS {

    public static void main(String[] args) {

        Department dept = new Department();


        Course c1 = new Course(1,"AAA");
        Course c2 = new Course(2, "BBB");

        Course c3 = new Course(3, "CCC");
        Course c4 = new Course(4, "DDD");

        dept.getCourseList1().add(c1);
        dept.getCourseList1().add(c2);

        dept.getCourseList2().add(c3);
        dept.getCourseList2().add(c4);

        SRS srs = new SRS();
        srs.registration(dept);
    }

    public void registration(Department dept) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to SRS!");
        System.out.println("Enter the student name");
        String studentName = sc.next();
        System.out.println("Enter the USN");
        String usn = sc.next();

        System.out.println("Displaying Elective 1 details"+dept.getCourseList1());

        System.out.println("Displaying Elective 2 details"+dept.getCourseList2());

        System.out.println("Enter the Elective 1 course id: ");
        int e1 = sc.nextInt();
        Course c1 = dept.getCourseById(dept.getCourseList1(), e1);

        System.out.println("Enter the Elective 2 course id: ");
        int e2 = sc.nextInt();
        Course c2 = dept.getCourseById(dept.getCourseList2(), e2);

        Student student = new Student(usn, studentName, c1, c2);
        dept.getStudentList().add(student);

        System.out.println(dept.getStudentList());
    }
}
