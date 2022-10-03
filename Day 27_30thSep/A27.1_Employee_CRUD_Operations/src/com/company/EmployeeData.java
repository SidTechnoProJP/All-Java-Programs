package com.company;

import java.util.*;

public class EmployeeData {
    static List<EmployeeData> employeeDataList = new ArrayList<EmployeeData>();
    static Scanner scInt = new Scanner(System.in);
    static Scanner scStr = new Scanner(System.in);
    private final String employeeName;
    private final int employeeID;
    private final String employeeDept;
    private final String employeeLocation;

    EmployeeData(String employeeName, int employeeID, String employeeDept, String employeeLocation) {
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.employeeDept = employeeDept;
        this.employeeLocation = employeeLocation;
    }

    public static void insertEmployeeDetails() {
        System.out.print("Enter Employee Name: ");
        String empName = scStr.next();
        System.out.print("Enter Employee ID: ");
        int empID = scInt.nextInt();
        System.out.print("Enter Employee Department: ");
        String empDept = scStr.next();
        System.out.print("Enter Employee Location: ");
        String empLoc = scStr.next();
        employeeDataList.add(new EmployeeData(empName, empID, empDept, empLoc));

    }

    public static void displayAllEmployeeDetails() {
        System.out.println("----------------------------------EMPLOYEE DATA-------------------------------------");
        for (EmployeeData e : employeeDataList) {
            System.out.println(e);
        }
        System.out.println("------------------------------------------------------------------------------------");
    }

    public static void searchEmployeeByID() {
        boolean found = false;
        System.out.println("Enter Employee ID to search: ");
        int empID = scInt.nextInt();
        System.out.println("------------------------------------------------------------------------------------");
        for (EmployeeData e : employeeDataList) {
            if (e.getEmployeeID() == empID) {
                System.out.println(e);
                found = true;
            }
        }
        System.out.println("------------------------------------------------------------------------------------");
        if (!found) {
            System.out.println("Record Not Found!!!");
        }
    }

    public static void deleteEmployeeByID() {
        boolean found = false;
        System.out.println("Enter Employee ID to delete: ");
        int empID = scInt.nextInt();
        System.out.println("------------------------------------------------------------------------------------");
        Iterator<EmployeeData> i = employeeDataList.iterator();
        while (i.hasNext()) {
            EmployeeData e = i.next();
            if (e.getEmployeeID() == empID) {
                i.remove();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Record Not Found!!!");
        } else {
            System.out.println("Record Deleted Successfully...");
        }
        System.out.println("------------------------------------------------------------------------------------");
    }

    public static void updateEmployeeByID() {
        boolean found = false;
        System.out.println("Enter Employee ID to update: ");
        int empID = scInt.nextInt();
        System.out.println("------------------------------------------------------------------------------------");
        ListIterator<EmployeeData> li = employeeDataList.listIterator();
        while (li.hasNext()) {
            EmployeeData e = li.next();
            if (e.getEmployeeID() == empID) {
                System.out.print("Enter Employee Name: ");
                String empName = scStr.next();
                System.out.print("Enter Employee Department: ");
                String empDept = scStr.next();
                System.out.print("Enter Employee Location: ");
                String empLoc = scStr.next();
                li.set(new EmployeeData(empName, empID, empDept, empLoc));
                found = true;
            }
        }
        if (!found) {
            System.out.println("Record Not Found!!!");
        } else {
            System.out.println("Record Updated Successfully...");
        }
        System.out.println("------------------------------------------------------------------------------------");
    }


    public int getEmployeeID() {
        return employeeID;
    }


    @Override
    public String toString() {
        return "\nEmployee{" +
                "employeeName='" + employeeName + '\'' +
                ", employeeID=" + employeeID +
                ", employeeDept='" + employeeDept + '\'' +
                ", employeeLocation='" + employeeLocation + '\'' +
                '}';
    }
}
