package com.demo.employee_CRUD_REST_API.service;

import com.demo.employee_CRUD_REST_API.model.EmployeeData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    List<EmployeeData> employeeDataList = new ArrayList<EmployeeData>();

    @Override
    public void insertEmployeeDetails(List<EmployeeData> employeeDataList) {
        this.employeeDataList.addAll(employeeDataList);
    }

    @Override
    public List<EmployeeData> displayAllEmployeeDetails() {
        return employeeDataList;
    }

    @Override
    public EmployeeData searchEmployeeByID(int id) {
        return employeeDataList.get(id - 1);
    }

    @Override
    public EmployeeData deleteEmployeeByID(int id) {
        return employeeDataList.remove(id - 1);
    }

    @Override
    public EmployeeData updateEmployeeByID(int id, EmployeeData employeeData) {

        return employeeDataList.set(id - 1, employeeData);
    }

}
