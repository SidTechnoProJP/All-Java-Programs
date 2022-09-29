package srs.newsrs;

import java.util.ArrayList;
import java.util.Scanner;


public class SRSDriver {
    public static void main(String[] args) {

        ArrayList<Course> courseList = new ArrayList<>();

        courseList.add(new Course("ENGG", 233));
        courseList.add(new Course("PHYS", 259));
        courseList.add(new Course("ENSF", 409));

        /*ArrayList<Course> courseList2 = new ArrayList<>();

        courseList2.add(new Course("DSA", 233));
        courseList2.add(new Course("OOPS", 259));
        courseList2.add(new Course("SDMA", 409));*/



        ArrayList<CourseOffering> engg233CourseSessions = new ArrayList<>();
        engg233CourseSessions.add(new CourseOffering(1, 40));
        engg233CourseSessions.add(new CourseOffering(2, 40));

        courseList.get(0).assignSessions(engg233CourseSessions);

        CourseCatalogue cat = new CourseCatalogue(courseList);

        Student s1 = new Student("Sam", 123);

        s1.addCourse(cat, "ENGG", 1);

        Scanner sc = new Scanner(System.in)

        System.out.println(s1.getStudentCourseList().get(0).getOffering());

        System.out.println(cat.getCourseList().get(0).getCourseSessions().get(0).getStudentList().get(0));
        s1.setName("Joe");
        System.out.println(cat.getCourseList().get(0).getCourseSessions().get(0).getStudentList().get(0));
    }
}
