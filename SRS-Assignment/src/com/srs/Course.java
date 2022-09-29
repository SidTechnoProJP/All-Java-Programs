package com.srs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Course {
    public String courseName;
    public String courseId;
    private int seat;
    static Map<String, Course> map = new HashMap<String, Course>();
    static Map<String, Course> map1 = new HashMap<String, Course>();

    Course() {
    }

    Course(String courseName, String courseId, int seat) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.seat = seat;

    }
    Course(String courseName,String courseId){
        this.courseName = courseName;
        this.courseId = courseId;
    }


    Scanner sc = new Scanner(System.in);

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public void decreaseAvailable() {
        this.seat--;
    }

    public void CreateProfessionalElective() {

        System.out.println("enter the no.of courses");
        int noOfCourses = sc.nextInt();
        Course course[] = new Course[noOfCourses];
        try {
            for (int i = 0; i < noOfCourses; i++) {
                System.out.println("enter the course name");
                courseName = sc.next();
                System.out.println("enter the course id");
                courseId = sc.next();
                System.out.println("enter the max seat");
                int seat = sc.nextInt();
                course[i] = new Course(courseName, courseId, seat);
                map.put(courseId, course[i]);
            }
        } catch (Exception e) {
            System.out.println("error");
        }

    }

    public void CreateOpenElective() {
        System.out.println("enter the no.of courses");
        int noOfCourses = sc.nextInt();
        Course course[] = new Course[noOfCourses];

        for (int i = 0; i < noOfCourses; i++) {
            System.out.println("enter the course name");
            courseName = sc.next();
            System.out.println("enter the course id");
            courseId = sc.next();
            System.out.println("enter the max seat");
            int seat = sc.nextInt();
            course[i] = new Course(courseName, courseId, seat);
            map1.put(courseId, course[i]);
        }
    }

    public static  void displayProfessional() {
        for (String n : map.keySet()) {
            System.out.println("professional elective course lists :" + map.get(n).courseName + " "
                    + map.get(n).courseId + " " + map.get(n).seat);
        }
    }

    public static void displayOpen() {

        for (String n : map1.keySet()) {
            System.out.println("open elective course lists :" + map1.get(n).courseName + " " + map1.get(n).courseId
                    + " " + map1.get(n).seat);
        }

    }

}

