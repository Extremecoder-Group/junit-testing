package com.badrul.junittesting.service;

import com.badrul.junittesting.entity.Employee;
import com.badrul.junittesting.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository repository;


    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    public Employee getEmployee(Long id)  {

        Optional<Employee> employee = repository.findById(id);
        return employee.orElseThrow(() -> new RuntimeException("sdfsdf"));
    }

}
