package com.demo.employee_CRUD_REST_API.service;

import com.demo.employee_CRUD_REST_API.model.EmployeeData;

import java.util.List;

public interface IEmployeeService {
    void insertEmployeeDetails(List<EmployeeData> employeeDataList);

    List<EmployeeData>  displayAllEmployeeDetails();

    EmployeeData searchEmployeeByID(int id);

    EmployeeData deleteEmployeeByID(int id);

   EmployeeData updateEmployeeByID(int id, EmployeeData employeeData);
}
