package com.badrul.junittesting;

import com.badrul.junittesting.entity.Employee;
import com.badrul.junittesting.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class JunittestingwithmockitoApplicationTests {

    @Autowired
    private WebApplicationContext context;
    ObjectMapper om = new ObjectMapper();
    private MockMvc mockMvc;
    @MockBean
    EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("Add Employee")
    void addEmployeeTest() throws Exception {
        Employee employee = Employee.builder().name("Rakib").email("rakibccj@gmail.com").mobile("01748141648").build();
        Employee expectedEmployee = Employee.builder().id(1L).name("badrul").email("rakibccj@gmail.com").mobile("01748141648").build();

        String jsonRequest = om.writeValueAsString(employee);

        when(employeeService.save(employee)).thenReturn(expectedEmployee);
        MvcResult result = mockMvc.perform(post("/employees").content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Employee response = om.readValue(resultContent, Employee.class);
        if (response.getName().equalsIgnoreCase("badrul"))
            System.out.println("right ans");
        assertEquals("017481416484", response.getMobile());
    }

    @Test
    @DisplayName("Get Employee By Id")
    void getEmployeeByIdTest() throws Exception {
        long id = 1L;
        when(employeeService.getEmployeeById(id)).
                thenReturn(Employee.builder().id(1L).name("Badrul Bro").mobile("01721-950890").build());

        MvcResult result = mockMvc
                .perform(get("/employees/" + id).content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Employee response = om.readValue(resultContent, Employee.class);
        assertEquals("Badrul Bro", response.getName());
    }

    @Test
    @DisplayName("Get Employee List")
    void getEmployeesTest() throws Exception {
        when(employeeService.getEmployees())
                .thenReturn(Stream.of(Employee.builder().id(1L).name("rezaul").mobile("01721-000000").build(),
                                Employee.builder().id(2L).name("Karim").mobile("909090").build())
                        .collect(Collectors.toList()));

        MvcResult result = mockMvc
                .perform(get("/employees").content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Employee[] response = om.readValue(resultContent, Employee[].class);
        assertEquals(2, response.length);
    }
}
