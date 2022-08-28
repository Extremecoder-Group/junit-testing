package com.badrul.junittesting.service;

import com.badrul.junittesting.entity.Employee;
import com.badrul.junittesting.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceIntegrationTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    private Employee akther;
    private Employee bodrul;
//    private Long akhterId;

    @BeforeEach
    void setup() {
        akther = Employee.builder().name("Akther").email("akther@gmail.com").build();
        bodrul = Employee.builder().name("Bodrul").email("bodurl@gmail.com").build();
    }

    @Test
    void testSave() {
        Employee savedEmployeeAkther = employeeService.save(akther);
        assertNotNull(savedEmployeeAkther.getId());
        assertEquals("Akther", savedEmployeeAkther.getName());
        assertEquals("akther@gmail.com", savedEmployeeAkther.getEmail());
        tearDown(Collections.singletonList(savedEmployeeAkther.getId()));
    }

    @Test
    void testGetEmployees() {
        List<Long> employeeIds = new ArrayList<>();
        Employee shahariar = employeeService.save(Employee.builder().name("Shahariar").build());
        Employee rakib = employeeService.save(Employee.builder().name("Rakib").build());

        employeeIds.add(shahariar.getId());
        employeeIds.add(rakib.getId());

        List<Long> fetchedEmployeeIds = employeeService.getEmployees().stream().map(Employee::getId).collect(Collectors.toList());
        assertTrue(fetchedEmployeeIds.containsAll(employeeIds));
        employeeRepository.deleteAllById(employeeIds);
    }

    void tearDown(List<Long> ids) {
        employeeRepository.deleteAllById(ids);
    }
}