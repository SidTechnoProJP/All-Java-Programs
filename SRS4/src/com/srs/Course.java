package com.srs;
import java.util.*;
import java.util.Map.Entry;


public class Course {
    public String courseName;
    int courseId;

    public Course(int courseId, String courseName) {
        this.courseName = courseName;
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseId=" + courseId +
                '}';
    }

}
