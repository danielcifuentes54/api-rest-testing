package com.dc.apiresttesting.repositories;

import com.dc.apiresttesting.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
