package com.pad.restapp.resources.rest;


import com.pad.restapp.domain.model.Employee;
import com.pad.restapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public HttpEntity getAllEmployees() {
        List<Employee> employees = employeeService.findAll();
    }





}
