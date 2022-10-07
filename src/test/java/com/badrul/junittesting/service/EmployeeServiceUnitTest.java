package com.badrul.junittesting.service;

import com.badrul.junittesting.entity.Employee;
import com.badrul.junittesting.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/*
 * Mock: dummy object.
 * Stub: When..then (BDD)
 * Spy:
 * */
@ExtendWith(MockitoExtension.class)
class EmployeeServiceUnitTest {
    private static Employee akther;
    private static Employee bodrul;

    @BeforeAll
    static void setupOnce() {
        akther = Employee.builder().id(1L).name("Akther").build();
        bodrul = Employee.builder().id(2L).name("Bodrul").build();
    }

//    @BeforeEach
//    void setup() {
//        akther = Employee.builder().id(1L).name("Akther").build();
//        bodrul = Employee.builder().id(2L).name("Bodrul").build();
//    }

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    // getEmployees()

    @Test
    @DisplayName("When getEmployees() is called then fetched all the employees")
    void testGetEmployees() {
        // AAA: Arrange, Act, Assert
        // Arrange
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(akther, bodrul));

        // Act
        List<Employee> employList = employeeService.getEmployees();

        // Assert
        assertEquals(2, employList.size());
        assertEquals(1L, employList.get(0).getId());
        assertEquals("Akther", employList.get(0).getName());
    }

    @Test
    void testSaveEmployee() {
        // Arrange
//        when(employeeService.save(akther)).thenReturn(akther);
        when(employeeService.save(any())).thenReturn(bodrul);

        // Act
        Employee aktherTest = employeeService.save(akther);

        // Assert
//        assertEquals("Akther", aktherTest.getName());
        assertEquals("Bodrul", aktherTest.getName());
    }

//    @AfterEach
//    void tearDown() {
//        akther = null;
//        bodrul = null;
//    }

    @AfterAll
    static void tearDownAll() {
        akther = null;
        bodrul = null;
    }
}
