package com.boot.rest.SpringBootWebDemo.service;

import com.boot.rest.SpringBootWebDemo.entity.Employee;

import com.boot.rest.SpringBootWebDemo.exception.RecordNotFoundException;
import com.boot.rest.SpringBootWebDemo.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public long getEmployeesCount()
    {
        return employeeRepository.count();
    }

    public Employee insertEmployee(Employee employee) throws com.boot.rest.SpringBootWebDemo.exception.RecordExistsException { //return Employee type, same as void(null)

        //there is only 1 method that does both save and update?
        if(employeeRepository.existsById(employee.getEmployeeId()))
            throw new com.boot.rest.SpringBootWebDemo.exception.RecordExistsException("employee with" + employee.getEmployeeId()+" already exists");

        Employee savedEntity = this.employeeRepository.save(employee);
        return savedEntity;
    }

    public Employee getEmployeeId(long empid) throws RecordNotFoundException {
        Optional<Employee> optEmpl = employeeRepository.findById(empid);
        if(optEmpl.isPresent())
            return optEmpl.get();
        throw new RecordNotFoundException("employee with" + empid + " does not exist");

//        return employeeRepository
//                .findById(empid)
//                .orElseThrow(() -> new RecordNotFoundException("\"employee with\" + empid + \" does not exist\""));
    }

    // Get all employees.
    public List<Employee> getAllEmployees()
    {
        return this.employeeRepository.findAll();
    }
    public Employee getEmployeeById(long empid) throws RecordNotFoundException {
        return employeeRepository.findById(empid)
                .orElseThrow(()->new RecordNotFoundException("employee with "+empid+" does not exist"));
    }
    // Get all employees by region.
    public List<Employee> getAllEmployeesByRegion(String region) {
        return this.employeeRepository.findEmployeeByRegion(region);
    }

    public void updateEmployee( Employee empToUpdate) throws RecordNotFoundException {
        System.out.println("UPDATE "+empToUpdate.getEmployeeId());
        if(! employeeRepository.existsById(empToUpdate.getEmployeeId()))
            throw new RecordNotFoundException("employee with "+empToUpdate.getEmployeeId()+" does not exist");
        employeeRepository.save(empToUpdate);
    }

    public void deleteEmployee(long employeeId) throws RecordNotFoundException {

        if(employeeRepository.existsById(employeeId))
            throw new RecordNotFoundException("employee with "+employeeId+" does not exist");
        employeeRepository.deleteById(employeeId);
    }
}
