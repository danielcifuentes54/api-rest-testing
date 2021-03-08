package com.dc.apiresttesting.resources;

import com.dc.apiresttesting.entities.Employee;
import com.dc.apiresttesting.exceptions.RecordNotFoundException;
import com.dc.apiresttesting.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/employee")
@RestController
public class EmployeeResource {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(path = "/")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok().body(employeeService.getAllEmployees());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws RecordNotFoundException {
        return ResponseEntity.ok().body(employeeService.findEmployeeById(id));
    }

    @PostMapping(path = "/")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok().body(employeeService.createEmployee(employee));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) throws RecordNotFoundException {
        return ResponseEntity.ok().body(employeeService.updateEmployee(id, employee));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.accepted().build();
    }
}
