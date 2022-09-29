package srs.newsrs;

import java.util.ArrayList;

public class Course {
    private String courseName;
    private int courseNum;
    private ArrayList<Course> preReq;
    private ArrayList<CourseOffering> courseSessions;

    public Course(String courseName, int num) {
        this.setCourseName(courseName);
        courseNum = num;
        preReq = new ArrayList<>();
        setCourseSessions(new ArrayList<>());
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void assignSessions(ArrayList<CourseOffering> sessions) {
        setCourseSessions(sessions);
    }

    public ArrayList<CourseOffering> getCourseSessions() {
        return courseSessions;
    }

    public void setCourseSessions(ArrayList<CourseOffering> courseSessions) {
        this.courseSessions = courseSessions;
    }

    public String toString() {
        return courseName + " " + courseNum;
    }
}
