package com.boot.rest.SpringBootWebDemo.controller;

//Create a REST controller, meant to expose data over REST API in the form of json or xml.

import com.boot.rest.SpringBootWebDemo.entity.Employee;
import com.boot.rest.SpringBootWebDemo.exception.RecordNotFoundException;
import com.boot.rest.SpringBootWebDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController// @Controller + @ResponseBody
@CrossOrigin // allows another application on different origin to access resource/data
@RequestMapping("/employees") //root mapping
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    // URI -> uniform resource locator
    // http://localhost:8080/employees/greet/get
    @RequestMapping(value = "/greet/get",method = RequestMethod.GET)
    public String greet(){ //how to reach this method?
        return "Hey !!";
    }

    //http://localhost:8080/employees/get
//    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @GetMapping
    public List<Employee> findAllEmployees(){ //how to reach this method?

        return this.employeeService.getAllEmployees();
    }



    /**
     * stop users access this uri from GET
     * @return
     */
    //@RequestMapping(value = "/post", method = RequestMethod.POST)
    @PostMapping
    public Employee addEmployees(){
        System.out.println("adding employees");
        //return this.employeeService.insertEmployee();
        return new Employee(101,"dummy",8976789,"dummy region");
    }

    //@RequestMapping(value = "/put", method = RequestMethod.PUT)
    @PutMapping
    public Employee updateEmployee(){
        System.out.println("adding employees");
        //return this.employeeService.insertEmployee();
        return new Employee(101,"dummy",8976789,"dummy region");
    }

    //@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @DeleteMapping
    public Employee deleteEmployeeById(){
        System.out.println("adding employees");
        //return this.employeeService.insertEmployee();
        return new Employee(101,"dummy",8976789,"dummy region");
    }

}
