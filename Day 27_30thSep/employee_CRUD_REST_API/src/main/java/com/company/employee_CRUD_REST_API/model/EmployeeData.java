package com.company.employee_CRUD_REST_API.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    //static List<EmployeeData> employeeDataList = new ArrayList<EmployeeData>();
    private String employeeName;
    private int employeeID;
    private String employeeDept;
    private String employeeLocation;

    EmployeeData(String employeeName, int employeeID, String employeeDept, String employeeLocation) {
        this.employeeName = employeeName;
        this.employeeID = employeeID;
        this.employeeDept = employeeDept;
        this.employeeLocation = employeeLocation;
    }

    EmployeeData() {
    }



    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeDept() {
        return employeeDept;
    }

    public void setEmployeeDept(String employeeDept) {
        this.employeeDept = employeeDept;
    }

    public String getEmployeeLocation() {
        return employeeLocation;
    }

    public void setEmployeeLocation(String employeeLocation) {
        this.employeeLocation = employeeLocation;
    }
}
