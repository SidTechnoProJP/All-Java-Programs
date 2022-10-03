package com.company.employee_CRUD_REST_API.service;

import com.company.employee_CRUD_REST_API.model.EmployeeData;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
    public List<EmployeeData>  displayAllEmployeeDetails() {
return employeeDataList;
    }

    @Override
    public EmployeeData searchEmployeeByID(int id) {
       return employeeDataList.get(id-1);
    }


}
