package com.srs;

import java.util.*;

public class SRS {
    public Course course;
    public Student student;
    int count = 0;
    Scanner sc = new Scanner(System.in);

    public void registration() {
        // System.out.println("enter the 1 subject of each group");
        System.out.println("enter the student name");
        String studentName = sc.next();
        System.out.println("enter the usn");
        String usn = sc.next();
        System.out.println("enter the semester");
        int sem = sc.nextInt();
        System.out.println("Display the professinal course details");
        Course.displayProfessional();
        System.out.println("display open elective course details");
        Course.displayOpen();

        System.out.println("enter the professional course id");
        String courseId = sc.next();
        System.out.println("enter the open elective course id");
        String courseId1 = sc.next();
        if ((course.map.containsKey(courseId) && course.map1.containsKey(courseId1))) {
            student = new Student(studentName, usn, sem);
         //   student.std.put(usn, student);
            String s=course.map.get(courseId).getCourseName();
            System.out.println("string s "+s);
            course = new Course(courseId, s);
         //   student.std.get(usn).cour.add( course);
        //    String st=student.std.get(usn).cour.get(0).courseId;
          //  System.out.println("done " +st );
        }

    }

}

