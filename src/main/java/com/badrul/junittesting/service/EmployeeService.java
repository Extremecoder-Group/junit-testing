package com.badrul.junittesting.service;

import com.badrul.junittesting.entity.Employee;
import com.badrul.junittesting.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public List<Employee> getEmployees() {
        return repository.findAll();
    }

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public Employee update(Employee employee, Long id) {
        getEmployee(id);
        employee.setId(id);
        return repository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return getEmployee(id);
    }

    public Employee getEmployee(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee Not found!"));
    }

}
