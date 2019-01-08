package com.pad.restapp.resources.rest;


import com.pad.restapp.domain.model.Employee;
import com.pad.restapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/list/", method = RequestMethod.GET)
    public HttpEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.findAll();

        if(employees.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            employees.forEach(c -> c.add(linkTo(methodOn(EmployeeController.class)
                    .getAllEmployees())
                    .withRel("employees")));
            employees.forEach(e -> e.add(linkTo(methodOn(EmployeeController.class)
                    .getEmployeeById(e.getEmployeeId()))
                    .withSelfRel()));
            return new ResponseEntity<>(employees, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public HttpEntity<Employee> getEmployeeById(@PathVariable("id") String employeeId) {

        Optional<Employee> maybeEmployee = employeeService.findById(employeeId);
        if (maybeEmployee.isPresent()) {

            Employee employee = maybeEmployee.get();
            employee.add(linkTo(methodOn(EmployeeController.class)
                    .getEmployeeById(employee.getEmployeeId()))
                    .withSelfRel());
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public HttpEntity<?> saveEmployee(@RequestBody Employee employee) {

        if(employeeService.employeeExists(employee)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        employeeService.saveEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
    public HttpEntity<?> updateEmployee(@PathVariable("id") String id, @RequestBody Employee e) {
        Optional<Employee> maybeEmployee = employeeService.findById(id);
        if(!maybeEmployee.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Employee employee = maybeEmployee.get();

            employee.setAge(e.getAge());
            employee.setName(e.getName());
            employee.setDepartmentId(e.getDepartmentId());
            employee.setStatus(e.getStatus());
            employeeService.updateEmployee(employee);
            employee.add(linkTo(methodOn(EmployeeController.class)
                    .getEmployeeById(employee.getEmployeeId()))
                    .withSelfRel());

            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
    }
}
