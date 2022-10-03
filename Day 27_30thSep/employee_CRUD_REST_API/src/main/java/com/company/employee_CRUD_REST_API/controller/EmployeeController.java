package com.company.employee_CRUD_REST_API.controller;

import com.company.employee_CRUD_REST_API.model.EmployeeData;
import com.company.employee_CRUD_REST_API.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController
{
    @Autowired
    private IEmployeeService employeeService;
    //mapping the getProduct() method to /product
    @GetMapping(value = "/employee")
    public List<EmployeeData> getEmployee()
    {
//finds all the products
        List<EmployeeData> employeeDataList = employeeService.displayAllEmployeeDetails();
//returns the product list
        return employeeDataList;
    }

    @PostMapping(value = "/addEmployees")
    public void addEmployees(@RequestBody List<EmployeeData> employeeDataList){
        employeeService.insertEmployeeDetails(employeeDataList);
    }

    @GetMapping(value = "/searchEmployee/{id}")
    public  EmployeeData searchEmployee(@PathVariable int id){
        EmployeeData e = employeeService.searchEmployeeByID(id);
        return e;
    }

}
