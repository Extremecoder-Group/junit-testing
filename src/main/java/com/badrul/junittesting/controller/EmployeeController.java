package com.badrul.junittesting.controller;

import com.badrul.junittesting.entity.Employee;
import com.badrul.junittesting.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        return new ResponseEntity<>(service.getEmployees(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> save(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(service.save(employee), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> update(@Valid @RequestBody Employee employee, @PathVariable Long id) {
        return new ResponseEntity<>(service.update(employee, id), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getEmployeeById(id), HttpStatus.OK);
    }
}
