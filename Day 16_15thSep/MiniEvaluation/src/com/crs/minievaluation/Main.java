package com.crs.minievaluation;

import java.util.Scanner;

public class Main {
    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student[] s = new Student[5];   //for 5 students

        int i, ch3;
        String studentsCourse;

        for (i = 0; i < 5; i++) {  //for initializing Student objects
            s[i] = new Student();
        }

        do {
            //Menu Driven
            System.out.println("WELCOME TO CRS");
            System.out.println("1. Admin");
            System.out.println("2. Student");
            System.out.print("Enter your choice: ");
            int ch1 = sc.nextInt();

            if (ch1 == 1) {
                System.out.println("Enter password: ");
                String pwd = sc.next();
                if (pwd.equals("admin")) {
                    do {
                        System.out.println("Welcome to CRS, Admin!");
                        System.out.println("1. Create New Course");
                        System.out.println("2. View Courses");
                        System.out.println("3. Search Course");
                        System.out.println("4. Edit Course");
                        System.out.println("5. Delete a Course");
                        System.out.println("6. Register Student for course");
                        System.out.println("7. Exit");
                        System.out.print("Enter your choice: ");
                        int ch2 = sc.nextInt();
                        switch (ch2) {
                            case 1:
                                for (i = 0; i < 5; i++) {
                                    s[i].courseGetData();
                                }
                                break;
                            case 2:
                                System.out.println("Course Code\t Course Name\t Course Fees");
                                for (i = 0; i < 5; i++)
                                    s[i].showCourse();
                                break;
                            case 3:
                                System.out.print("Enter the course to search: ");
                                studentsCourse = sc.next();
                                for (i = 0; i < 5; i++) {
                                    if (studentsCourse.equals(Admin.courseName)) {
                                        System.out.println(Admin.courseCode + "\t" + Admin.courseName + "\t" + Admin.courseFees);
                                        break;
                                    }
                                }
                                break;
                            case 6:
                                System.out.print("Enter the Student Id to search=");
                                int student_id = sc.nextInt();
                                for (i = 0; i < 5; i++) {
                                    if (student_id == Student.studentId) {
                                        System.out.println(Student.studentId + "\t" + Student.studentName + "\t" + Student.studentCourseName);
                                        break;
                                    }
                                }
                                break;
                            case 7:
                                break;

                            default:
                                System.out.println("Invalid Input!");
                        }
                        System.out.print("Do you want to continue: ");
                        ch3 = sc.nextInt();
                    } while (ch3 != 4);

                } else
                    System.out.println("Incorrect Password!!");

            } else if (ch1 == 2) {
                do {
                    System.out.println("\n*****Student Course Registration System*****");
                    System.out.println("1. Insert the Student data");
                    System.out.println("2. Display Student data");
                    System.out.println("3. View all Courses");
                    System.out.println("4. View all available Courses");
                    System.out.println("5. Register in a Course");
                    System.out.println("6. Withdraw from Course");
                    System.out.println("7. Show all Registered Courses");
                    System.out.println("8. Exit");
                    System.out.print("Enter your choice: ");
                    int ch2 = sc.nextInt();
                    switch (ch2) {
                        case 1:
                            for (i = 0; i < 5; i++) {
                                s[i].studentGetData();
                            }
                            break;
                        case 2:
                            System.out.println("Student code\t Name\t Course");
                            for (i = 0; i < 5; i++)
                                s[i].studentPutData();
                            break;
                        case 3:
                            System.out.println("Showing all Courses");
                            System.out.println("Course Code\t Course Name\t Course Fees");
                            for (i = 0; i < 5; i++)
                                s[i].showCourse();
                            break;
                        case 4:
                            System.out.println("Showing all available Courses");
                            System.out.println("Course Code\t Course Name\t Course Fees");
                            for (i = 0; i < 5; i++)
                                s[i].showCourse();
                            break;

                        case 5:
                            System.out.print("Enter the Course to Register: ");
                            studentsCourse = sc.next();
                            for (i = 0; i < 5; i++) {
                                if (studentsCourse.equals(Admin.courseName)) {
                                    System.out.println(Admin.courseCode + "\t" + Admin.courseName + "\t" + Admin.courseFees);
                                    break;
                                }
                            }
                            break;
                        case 6:
                            System.out.print("Enter the Course to Withdraw: ");
                            studentsCourse = sc.next();
                            for (i = 0; i < 5; i++) {
                                if (studentsCourse.equals(Admin.courseName)) {
                                    System.out.println(Admin.courseCode + "\t" + Admin.courseName + "\t" + Admin.courseFees);
                                    break;
                                }
                            }
                            break;
                        case 7:
                            System.out.println("Showing all Registered Courses");
                            System.out.println("Course Code\t Course Name\t Course Fees");
                            for (i = 0; i < 5; i++)
                                s[i].showCourse();
                            break;

                        case 8:
                            break;

                        default:
                            System.out.println("Invalid Input!");
                    }
                    System.out.print("Do you want to continue: ");
                    ch3 = sc.nextInt();
                } while (ch3 != 4);
            }
            System.out.print("Do you want to continue: ");
            ch3 = sc.nextInt();
        } while (ch3 != 0);
    }
}



