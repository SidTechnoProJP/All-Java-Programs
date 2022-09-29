package srs.newsrs;

import java.util.ArrayList;

public class Student {

    private String name;
    private int id;
    private ArrayList<Registration> studentRegList;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        studentRegList = new ArrayList<Registration>();
    }

    public void addCourse(CourseCatalogue cat, String courseName, int secNum) {

        Registration reg = cat.addCourse(courseName, secNum, this);
        if (reg == null) {
            System.err.println("Can't add course!");
            return;
        }
        studentRegList.add(reg);
    }

    public ArrayList<Registration> getStudentCourseList() {
        return studentRegList;
    }

    public void setStudentRegList(ArrayList<Registration> studentCourseList) {
        this.studentRegList = studentRegList;
    }

    public String toString() {
        return name + " " + id;
    }

    public void setName(String st) {
        name = st;
    }

}