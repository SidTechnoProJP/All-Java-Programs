package com.srs;

public class Student {
    String usn;
    String name;

    @Override
    public String toString() {
        return "Student{" +
                "usn='" + usn + '\'' +
                ", name='" + name + '\'' +
                ", c1=" + c1 +
                ", c2=" + c2 +
                '}';
    }

    Course c1;
    Course c2;

    public Student(String usn, String name, Course c1, Course c2) {
        this.usn = usn;
        this.name = name;
        this.c1 = c1;
        this.c2 = c2;
    }
}
