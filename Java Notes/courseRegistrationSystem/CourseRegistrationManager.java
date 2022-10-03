package courseRegistrationSystem;

import java.util.Scanner;

public class CourseRegistrationManager {
    static Scanner scan = new Scanner(System.in);
    static boolean mainswitch = true;
    static boolean subswitch = true;
    static int choice;

    public static void main(String[] args) {

        //check admin or Student
        while (mainswitch) {
            System.out.println("Welcome to Course System !!\n1.admin\n2.Student\n3.exit");
            subswitch = true;
            choice = scan.nextInt();
            switch (choice) {
                case 1:
                    adminOperation();
                    break;

                case 2:
                    StudentOperation();
                    break;
                case 3:
                    return;
            }
        }

    }

    private static void StudentOperation() {
        Student student = new Student();
        while (subswitch) {
            System.out.println("\n1.View all courses \n2. View all courses that are NOT full \n3.Register in a course\n4.Withdraw from a course\n5.View all courses You Registered in \n6. exit");
            choice = scan.nextInt();
            switch (choice) {

                case 1:
                    student.viewAllCourseDetails();
                    break;
                case 2:
                    student.viewAllCourseNotFull();
                    break;
                case 3:
                    student.registerToCourse();
                    break;
                case 4:
                    student.withdrawCourse();
                    break;
                case 5:
                    student.displayRegisteredCourses();
                    break;

                case 6:
                    subswitch = false;

                    break;
            }
        }
    }

    private static void adminOperation() {
        Admin admin = new Admin();
        while (subswitch) {
            System.out.println("\n1.Create a new course \n2. Delete a course\n3.Edit a course (except for course ID and name) \n4.Display info for a course (by course ID) \n5. Register a student\n6.View all courses \n7.Exit");
            choice = scan.nextInt();
            switch (choice) {

                case 1:
                    admin.createCourse();
                    break;
                case 2:
                    admin.deleteCourse();
                    break;
                case 3:
                    admin.editCourse();
                    break;
                case 4:
                    admin.displayCourseDetails();
                    break;
                case 5:
                    admin.registerStudent();
                    break;
                case 6:
                    admin.viewAllCourseDetails();
                    break;

                case 7:
                    subswitch = false;
                    break;
            }
        }

    }


}


