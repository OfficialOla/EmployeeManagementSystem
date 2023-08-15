package com.example.EmployeeManagementSystem.services;

import com.example.EmployeeManagementSystem.dto.request.EmployeeDto;
import com.example.EmployeeManagementSystem.exception.ResourceNotFoundException;
import com.example.EmployeeManagementSystem.models.Employee;
import com.example.EmployeeManagementSystem.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        return buildCreateEmployeeResponse(savedEmployee);
    }

    private EmployeeDto buildCreateEmployeeResponse(Employee savedEmployee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(savedEmployee.getFirstName());
        employeeDto.setLastName(savedEmployee.getLastName()) ;
        employeeDto.setEmail(savedEmployee.getEmail());
        employeeDto.setId(savedEmployee.getId());
        return employeeDto;
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = Collections.singletonList(modelMapper.map(employees, EmployeeDto.class));
        return employeeDtos;
    }

    @Override
    public EmployeeDto getEmployeeById(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee does not exist with id: " + employeeId));
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployee(long employeeId, EmployeeDto employeeDto) {
         Employee existingEmployee = employeeRepository.findById(employeeId)
                 .orElseThrow(()->
                         new ResourceNotFoundException("Employee does not exist with id: " +employeeId));
         existingEmployee.setFirstName(employeeDto.getFirstName());
         existingEmployee.setLastName(existingEmployee.getLastName());
         existingEmployee.setEmail(existingEmployee.getEmail());

         employeeRepository.save(existingEmployee);
         return buildCreateEmployeeResponse(existingEmployee);

    }

    @Override
    public void deleteEmployee(long employeeId) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Employee does not exist with id: " + employeeId));
        employeeRepository.deleteById(existingEmployee.getId());

    }
}
