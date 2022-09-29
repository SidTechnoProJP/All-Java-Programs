package com.srs.assignment;
//user
public class Student {
    int studentRollNo;
    String studentName;
    String courseName;

    Student(int studentRollNo, String studentName, String courseName) {
        this.studentRollNo = studentRollNo;
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public int getStudentRollNo() {
        return studentRollNo;
    }

    public void setStudentRollNo(int studentRollNo) {
        this.studentRollNo = studentRollNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}