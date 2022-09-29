package srs.newsrs;

import java.util.ArrayList;

public class CourseCatalogue {
    private ArrayList<Course> courseList;

    public CourseCatalogue(ArrayList<Course> list) {
        courseList = list;
    }

    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public Course searchCatalogue(String courseName) {

        for (Course c : courseList) {
            if (courseName.equals(c.getCourseName()))
                return c;
        }
        return null;
    }

    public CourseOffering searchSection(Course selectedCourse, int secNum) {
        for (CourseOffering co : selectedCourse.getCourseSessions()) {
            if (co.getSecNum() == secNum)
                return co;
        }
        return null;
    }

    public Registration addCourse(String courseName, int sectionNumber, Student st) {

        Course selectedCourse = searchCatalogue(courseName);
        if (selectedCourse == null)
            return null;

        CourseOffering co = searchSection(selectedCourse, sectionNumber);
        if (co == null)
            return null;

        Registration reg = new Registration();
        reg.setOffering(co);
        reg.setTheStudent(st);

        co.addRegistration(reg, selectedCourse);

        return reg;
    }
}
