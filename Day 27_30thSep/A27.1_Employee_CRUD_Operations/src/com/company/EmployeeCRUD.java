package com.company;

import java.util.Scanner;

public class EmployeeCRUD {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("""
                    \n*****MENU*****
                    1. Insert Employee Details
                    2. Display All Employee Details
                    3. Search Employee by ID
                    4. Delete Employee by ID
                    5. Update Employee Details by ID
                    6. Exit""");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> EmployeeData.insertEmployeeDetails();
                case 2 -> EmployeeData.displayAllEmployeeDetails();
                case 3 -> EmployeeData.searchEmployeeByID();
                case 4 -> EmployeeData.deleteEmployeeByID();
                case 5 -> EmployeeData.updateEmployeeByID();
            }

        } while (choice != 6);
    }
}