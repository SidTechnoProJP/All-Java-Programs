package srs.newsrs;

import java.util.ArrayList;

public class CourseOffering {
    private int secNum;
    private ArrayList<Registration> studentList;
    private Course theCourse;

    public CourseOffering(int secNum, int cap) {
        this.setSecNum(secNum);
        setStudentList(new ArrayList<>());
    }

    public Course getTheCourse() {
        return theCourse;
    }

    public void setTheCourse(Course c) {
        theCourse = c;
    }

    public void addRegistration(Registration reg, Course c) {
        getStudentList().add(reg);
        setTheCourse(c);
    }

    public int getSecNum() {
        return secNum;
    }

    public void setSecNum(int secNum) {
        this.secNum = secNum;
    }

    public String toString() {
        return "Section Number: " + secNum;
    }

    public ArrayList<Registration> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<Registration> studentList) {
        this.studentList = studentList;
    }
}
