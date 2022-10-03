package com.demo.employee_CRUD_REST_API.controller;

import com.demo.employee_CRUD_REST_API.model.EmployeeData;
import com.demo.employee_CRUD_REST_API.service.IEmployeeService;
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
return employeeService.searchEmployeeByID(id);
    }

    @DeleteMapping(value = "/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable int id) {employeeService.deleteEmployeeByID(id);}

    @PutMapping(value = "/updateEmployee/{id}")
    public EmployeeData updateEmployee(@PathVariable int id, @RequestBody EmployeeData employeeData){
        return employeeService.updateEmployeeByID(id, employeeData);
    }




}
