package com.example.EmployeeManagementSystem.services;

import com.example.EmployeeManagementSystem.dto.request.EmployeeDto;
import com.example.EmployeeManagementSystem.models.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployee();

    EmployeeDto getEmployeeById(long employeeId);

    EmployeeDto updateEmployee(long employeeId, EmployeeDto employeeDto);

    void deleteEmployee(long employeeId);

}
