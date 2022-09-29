package com.srs;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private Course course;
// make srs obj here srs should be seprate class
    List<Student> studentList = new ArrayList<>();
    List<Course> courseList1 = new ArrayList<>();
    List<Course> courseList2 = new ArrayList<>();



    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Course> getCourseList1() {
        return courseList1;
    }

    public void setCourseList1(List<Course> courseList1) {
        this.courseList1 = courseList1;
    }

    public List<Course> getCourseList2() {
        return courseList2;
    }

    public void setCourseList2(List<Course> courseList2) {
        this.courseList2 = courseList2;
    }

    public Course getCourseById(List<Course> courseList, int id){
        for (Course c: courseList){
            if (c.courseId == id)
                return c;
        }
        return null;
    }
}
