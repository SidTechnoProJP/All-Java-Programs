package courseRegistrationSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Student {


    private String studentName;
    private String usn;
    private String section;

    private ArrayList<CourseDetails> courses = new ArrayList<>();

    Student() {
        System.out.println("Hello Student!!!");
    }

    Student(String studentName, String usn, String section) {
        this.studentName = studentName;
        this.usn = usn;
        this.section = section;
    }
//
//    /**
//     * getter method for studentName
//     *
//     * @return studentName
//     */
//    public String getStudentName() {
//        return studentName;
//    }
//
//    /**
//     * getter method for USN
//     *
//     * @return usn
//     */
//    public String getUsn() {
//        return usn;
//    }
//
//    /**
//     * getter method for Section
//     *
//     * @return section
//     */
//    public String getSection() {
//        return section;
//    }

    /**
     * Display All the Registered courses
     */
    public void viewAllCourseDetails() {
        if (RegisterationRecords.getInstance().courses.size() > 0) {
            System.out.println("Available Courses Details are:\nCourse Id\t\tCourse Name\t\tDuration\t\tSeats Left");
            RegisterationRecords.getInstance().courses.forEach((courseName, course) -> System.out.println(course.getCourseId() + "\t\t\t\t" + course.getCourseName() + "\t\t\t\t" + course.getDurationOfCourse() + "\t\t\t\t" + course.getSeatsLeft()));
        } else {
            System.out.println("No courses added yet!!");
        }
    }

    /**
     * Display only  the Registered courses which are not full
     */
    public void viewAllCourseNotFull() {
        if (RegisterationRecords.getInstance().courses.size() > 0) {
            System.out.println("Available Courses Details are:\nCourse Id\t\tCourse Name\t\tDuration\t\tSeats Left");
            RegisterationRecords.getInstance().courses.forEach((courseName, course) -> {
                if (course.getSeatsLeft() > 0)
                    System.out.println(course.getCourseId() + "\t\t\t\t" + course.getCourseName() + "\t\t\t\t" + course.getDurationOfCourse() + "\t\t\t\t" + course.getSeatsLeft());
            });
        } else {
            System.out.println("No courses added yet!!");
        }
    }

    /**
     * Register for the course available
     */
    public void registerToCourse() {
        Scanner scan = new Scanner(System.in);
        RegisterationRecords registerationRecords = RegisterationRecords.getInstance();
        String courseName, studentName, section;
        System.out.println("Enter your Name: ");
        studentName = scan.nextLine();
        System.out.println("Section: ");
        section = scan.next();
        if (registerationRecords.students.containsKey(studentName + "_" + section)) {
            if (RegisterationRecords.getInstance().courses.size() > 0) {
                scan = new Scanner(System.in);
                System.out.println("course Name: ");
                courseName = scan.nextLine();
                String courseId = getCourseIdByName(courseName);
                CourseDetails courseDetails = registerationRecords.courses.get(courseId);
                if (courseDetails.getSeatsLeft() > 0) {
                    Student student = registerationRecords.students.get(studentName + "_" + section);
                    courseDetails.decreaseSeatsAvailable();
                    student.courses.add(courseDetails);
                } else
                    System.out.println("Seats are full for this Course");
            } else {
                System.out.println("No courses added yet!!");
            }
        } else {
            System.out.println("You are not a Registered Student");
        }


    }

    /**
     * Withdraw from  the course which the student is registered to
     */
    void withdrawCourse() {
        Scanner scan = new Scanner(System.in);
        RegisterationRecords registerationRecords = RegisterationRecords.getInstance();
        String courseName, studentName;
        System.out.println("Enter your Name: ");
        studentName = scan.nextLine();
        String id = getStudentIdByName(studentName);
        if (id != null) {
            if (registerationRecords.students.get(id).courses.size() > 0) {
                System.out.println("course Name: ");
                courseName = scan.nextLine();
                String courseId = getCourseIdByName(courseName);
                if (courseId != null) {

                    CourseDetails courseDetails = registerationRecords.courses.get(courseId);
                    courseDetails.increaseSeatsAvailable();
                    registerationRecords.students.get(id).courses.remove(courseDetails);

                } else {
                    System.out.println("Invalid Course id. Enter proper course id");
                }
            } else
                System.out.println("Not Registered to any Courses");
        } else {
            System.out.println("You are not a Registered Student");
        }
    }

    /**
     * Display  all the courses that student is registered in
     */
    void displayRegisteredCourses() {
        String studentName, section;
        RegisterationRecords registerationRecords = RegisterationRecords.getInstance();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your Name: ");
        studentName = scan.nextLine();
        System.out.println("Section: ");
        section = scan.next();
        if (registerationRecords.students.containsKey(studentName + "_" + section)) {
            Student student = registerationRecords.students.get(studentName + "_" + section);
            //if student didn't registered for any courses display appropriate message else display courses registered
            if (student.courses.size() == 0)
                System.out.println("Not Registered to any Courses");
            else
                student.courses.forEach(courseDetails -> System.out.println(courseDetails.getCourseName()));

        } else {
            System.out.println("You are not a Registered Student");
        }

    }

    /**
     * Get the course Id by passing course name to the function
     *
     * @param name for which id should be returned
     * @return courseId
     */
    private String getCourseIdByName(String name) {
        final String[] id = {null};
        RegisterationRecords.getInstance().courses.forEach((courseId, courseDetails) -> {
            if (courseDetails.getCourseName().equals(name)) {
                id[0] = courseId;
            }
        });
        //if name not found in the list user entered display appropriate message
        if (id[0] == null)
            System.out.println("Invalid Course Name!!");
        return id[0];
    }

    /**
     * Get the student Id by passing student name to the function
     *
     * @param name for which id should be returned
     * @return studentId
     */
    private String getStudentIdByName(String name) {
        final String[] id = {null};
        RegisterationRecords.getInstance().students.forEach((studentId, student) -> {
            if (student.studentName.equals(name)) {
                id[0] = studentId;
            }
        });
        //if name not found in the list user entered display appropriate message
        if (id[0] == null)
            System.out.println("Enter your full Registered Name!!");
        return id[0];
    }
}
