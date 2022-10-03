package com.company.employee_CRUD_REST_API.service;

import com.company.employee_CRUD_REST_API.model.EmployeeData;

import java.util.List;

public interface IEmployeeService {
    void insertEmployeeDetails(List<EmployeeData> employeeDataList);

    List<EmployeeData>  displayAllEmployeeDetails();

    EmployeeData searchEmployeeByID(int id);
}
