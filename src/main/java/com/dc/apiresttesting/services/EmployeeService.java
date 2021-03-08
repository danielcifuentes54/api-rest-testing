package com.dc.apiresttesting.services;

import com.dc.apiresttesting.entities.Employee;
import com.dc.apiresttesting.exceptions.RecordNotFoundException;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee findEmployeeById(Long id) throws RecordNotFoundException;

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Long employeeId, Employee newEmployee) throws RecordNotFoundException;

    void deleteEmployee(Long id);
}
