package com.example.EmployeeManagementSystem.repositories;

import com.example.EmployeeManagementSystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
