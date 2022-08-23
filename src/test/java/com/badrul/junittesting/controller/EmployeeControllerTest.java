package com.badrul.junittesting.controller;

import com.badrul.junittesting.entity.Employee;
import com.badrul.junittesting.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeControllerTest.class)
class EmployeeControllerTest {

    @MockBean
    private EmployeeRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll() {
//        log.info("*** EmployeeController class test case execution started***");
    }

    @Test
    @DisplayName("EmployeeController.save() user creation")
    void save() throws Exception {
//        log.info("*** EmployeeController.save() execution started ***");

        Employee employee = Employee.builder().id(2L).name("Badrul").email("badrul@gmail.com").address("Jashore").build();

//        log.debug("Employee object created as: " + employee.toString());

        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employee))).andExpect(status().isCreated()).andDo(print());

//        log.info("*** EmployeeController.save() execution started ***");
    }
}
