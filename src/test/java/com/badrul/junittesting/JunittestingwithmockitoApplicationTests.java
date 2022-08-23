package com.badrul.junittesting;

import com.badrul.junittesting.entity.Employee;
import com.badrul.junittesting.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
    void addEmployeeTest() throws Exception {
        Employee employee = Employee.builder().name("Rakib").email("rakibccj@gmail.com").mobile("01748141648").build();

        String jsonRequest = om.writeValueAsString(employee);

        MvcResult result = mockMvc.perform(post("/employees").content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated()).andReturn();

        String resultContent = result.getResponse().getContentAsString();
        Employee response = om.readValue(resultContent, Employee.class);
        Assertions.assertTrue(response.getMobile().equalsIgnoreCase("017481416484"));
    }

    @Test
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
}
