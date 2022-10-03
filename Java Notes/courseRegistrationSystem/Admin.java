package courseRegistrationSystem;

import java.util.Scanner;

public class Admin {


    /**
     * create the new course by taking inputs from user and store
     */
    public void createCourse() {
        Scanner scan = new Scanner(System.in);
        String courseId, courseName;
        int seatsLeft, durationOfCourse;
        System.out.println("Enter Course Details");
        System.out.println("course Name: ");
        courseName = scan.nextLine();
        while (true) {
            System.out.println("course id (ex:18cs112):");
            courseId = scan.next();
            //check for the formate
            if (courseId.matches("[0-9]{2}[A-Za-z]{2}[0-9]{3}")) {
                break;
            } else {
                System.out.println("Enter course id in the format of example provided");
            }
        }
        System.out.println("Duration Of Course(in months):");
        durationOfCourse = scan.nextInt();
        System.out.println("number of seats:");
        seatsLeft = scan.nextInt();
        CourseDetails courseDetails = new CourseDetails(courseId, courseName, seatsLeft, durationOfCourse);
        if (!RegisterationRecords.getInstance().courses.containsKey(courseId))
            RegisterationRecords.getInstance().courses.put(courseId, courseDetails);
        else
            System.out.println("Course with same id already Registered");

    }

    /**
     * Delete the particular course from the record 'courses'
     */
    public void deleteCourse() {
        if (RegisterationRecords.getInstance().courses.size() > 0) {
            Scanner scan = new Scanner(System.in);
            String courseId;
            System.out.println("Enter course Id to delete :");
            courseId = scan.next();
            if (findCourse(courseId) != null)
                RegisterationRecords.getInstance().courses.remove(courseId);
            else
                System.out.println("Course not found");
        } else {
            System.out.println("No courses added yet!!");
        }
    }

    /**
     * Edit the details of the course (except for course ID and name)
     */
    public void editCourse() {
        if (RegisterationRecords.getInstance().courses.size() > 0) {
            Scanner scan = new Scanner(System.in);
            String courseId;
            int seatsLeft, durationOfCourse;
            System.out.println("Enter course Id to Edit :");
            courseId = scan.next();
            CourseDetails courseToEdit = findCourse(courseId);
            //if course is present then take values and edit the details
            if (courseToEdit != null) {

                System.out.println("Duration Of Course(in months):");
                durationOfCourse = scan.nextInt();
                System.out.println("number of seats:");
                seatsLeft = scan.nextInt();
                courseToEdit.setDurationOfCourse(durationOfCourse);
                courseToEdit.setSeatsLeft(seatsLeft);
            } else
                System.out.println("Course not found");

        } else {
            System.out.println("No courses added yet!!");
        }
    }

    public void displayCourseDetails() {
        if (RegisterationRecords.getInstance().courses.size() > 0) {
            String courseId;
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter course Id to display details:");
            courseId = scan.next();
            CourseDetails course = findCourse(courseId);
            //if course is present then display the details
            if (course != null) {

                System.out.println("Details of the course:");
                System.out.println("course Id:" + course.getCourseId());
                System.out.println("course Name:" + course.getCourseName());
                System.out.println("course Duration :" + course.getDurationOfCourse());
                System.out.println("number of seats:" + course.getSeatsLeft());


            } else
                System.out.println("Course not found");
        } else {
            System.out.println("No courses added yet!!");
        }
    }


    /**
     * Register the new students to the registration Record
     */
    public void registerStudent() {
        Scanner scan = new Scanner(System.in);
        String studentName, usn;
        String section;
        System.out.println("Enter Student Details");
        System.out.println(" Name: ");
        studentName = scan.nextLine();
        while (true) {
            System.out.println("USN:");
            usn = scan.next();
            //check for the formate
            if (usn.matches("[0-9]{1}[A-Za-z]{2}[0-9]{2}[A-Za-z]{2}[0-9]{3}")) {
                break;
            } else {
                System.out.println("Enter usn in the proper format example : 4sf18cs001");
            }
        }
        System.out.println("Section:");
        section = scan.next();
        Student student = new Student(studentName, usn, section);
        String id = genrateIdForStudent(studentName, section);
        if (!RegisterationRecords.getInstance().students.containsKey(id))
            RegisterationRecords.getInstance().students.put(id, student);
        else
            System.out.println("Student with same name already exist in the section");
    }

    /**
     * Display Al the Registered courses
     */
    void viewAllCourseDetails() {
        if (RegisterationRecords.getInstance().courses.size() > 0) {
            System.out.println("Courses Details are:\nCourse Id\t\tCourse Name\t\tDuration\t\tSeats Left");
            RegisterationRecords.getInstance().courses.forEach((courseName, course) -> System.out.println(course.getCourseId() + "\t\t\t\t" + course.getCourseName() + "\t\t\t\t" + course.getDurationOfCourse() + "\t\t\t\t" + course.getSeatsLeft()));
        } else {
            System.out.println("No courses added yet!!");
        }
    }


    /**
     * genrate the student id by taking studentName and section
     *
     * @param studentName name of the student
     * @param section     section to which the student belongs
     * @return id for the student
     */
    private String genrateIdForStudent(String studentName, String section) {
        return studentName + "_" + section;
    }

    /**
     * find the course present in the record or not
     *
     * @param courseId to which course name should be returned
     * @return null if courseId not there in the record
     */
    private CourseDetails findCourse(String courseId) {
        return RegisterationRecords.getInstance().courses.get(courseId);
    }


}
