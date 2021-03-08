package com.dc.apiresttesting.services;

import com.dc.apiresttesting.entities.Employee;
import com.dc.apiresttesting.exceptions.RecordNotFoundException;
import com.dc.apiresttesting.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long id) throws RecordNotFoundException {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("The employee with id:" + id + " does not exist"));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long employeeId, Employee newEmployee) throws RecordNotFoundException {
        Employee employee = findEmployeeById(employeeId);
        employee.setName(newEmployee.getName());
        employee.setDesignation(newEmployee.getDesignation());
        employee.setSalary(newEmployee.getSalary());
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
