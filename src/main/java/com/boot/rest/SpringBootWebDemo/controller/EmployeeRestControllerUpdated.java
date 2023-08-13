package com.boot.rest.SpringBootWebDemo.controller;

import com.boot.rest.SpringBootWebDemo.entity.Employee;
import com.boot.rest.SpringBootWebDemo.exception.RecordExistsException;
import com.boot.rest.SpringBootWebDemo.exception.RecordNotFoundException;
import com.boot.rest.SpringBootWebDemo.service.EmployeeService;
import com.boot.rest.SpringBootWebDemo.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emps")
public class EmployeeRestControllerUpdated {

    @Autowired
    private EmployeeService employeeService;

    // ? => query parameters and they are optional
    // http://localhost:8080/emps?region=
    @GetMapping
    public List<Employee> getAllEmployees(
            @RequestParam(required = false) String region){
        if(region == null)
            return employeeService.getAllEmployees();
        return employeeService.getAllEmployeesByRegion(region);
    }

    /**
     * custom messages => empid exists => success, empdata
     * does not exist => failure, no emp exist
     * @param empid
     */
//    @GetMapping("/{empid}")
//    public Employee getEmployeeById(@PathVariable long empid){
//        try {
//            return this.employeeService.getEmployeeById(empid);
//        } catch (RecordNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
    @GetMapping("/{empid}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable long empid){
        try {
            Employee employee = this.employeeService.getEmployeeById(empid);
            Map<Object, Object> map = new HashMap<>();
            map.put(Messages.SUCCESS,"Employee found");
            map.put("employees",employee);
            return ResponseEntity.status(HttpStatus.FOUND).body(map);
        } catch (RecordNotFoundException e) {
            Map<Messages, String> map = new HashMap<>();
            map.put(Messages.FAILURE, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){

        try {
            return this.employeeService.insertEmployee(employee);
        } catch (RecordExistsException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //Json employee is converted to Employee when you add @RequestBody
    @PutMapping
    public void updateEmployee(@RequestBody Employee employee){
        try {
            this.employeeService.updateEmployee(employee);
        } catch (RecordNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    @DeleteMapping("/{empid}")
    public void deleteEmployee(@PathVariable long empid){
        try {
            this.employeeService.deleteEmployee(empid);
        } catch (RecordNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
