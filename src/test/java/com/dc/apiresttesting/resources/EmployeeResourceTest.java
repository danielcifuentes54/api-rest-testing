package com.dc.apiresttesting.resources;

import com.dc.apiresttesting.entities.Employee;
import com.dc.apiresttesting.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@WebMvcTest(EmployeeResource.class)
public class EmployeeResourceTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    EmployeeService employeeService;

    @Test
    public void getAllEmployees() throws Exception {

        mvc.perform( MockMvcRequestBuilders
                .get("/api/employee/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json("[]"));

        Mockito.verify(employeeService, Mockito.times(1)).getAllEmployees();
    }

    @Test
    public void getEmployeeById() throws Exception {

        Mockito.when(employeeService.findEmployeeById(1l))
                .thenReturn(Employee.builder()
                        .id(1l)
                        .name("Daniel")
                        .designation("Microservice Architect")
                        .salary(BigDecimal.valueOf(10000l))
                        .build());

        mvc.perform( MockMvcRequestBuilders
                        .get("/api/employee/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Daniel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value("10000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.designation").value("Microservice Architect"));

    }

    @Test
    public void createEmployeeAPI() throws Exception {

        Mockito.when(employeeService.createEmployee(Mockito.any()))
                .thenReturn(Employee.builder()
                        .id(1l)
                        .name("Daniel")
                        .designation("Microservice Architect")
                        .salary(BigDecimal.valueOf(10000l))
                        .build());

        mvc.perform( MockMvcRequestBuilders
                .post("/api/employee/")
                .content(asJsonString(Employee.builder()
                        .id(1l)
                        .name("Daniel")
                        .designation("Microservice Architect")
                        .salary(BigDecimal.valueOf(10000l))
                        .build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Daniel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value("10000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.designation").value("Microservice Architect"));
    }

    @Test
    public void updateEmployee() throws Exception {

        Mockito.when(employeeService.updateEmployee(Mockito.anyLong(), Mockito.any()))
                .thenReturn(Employee.builder()
                        .id(1l)
                        .name("Daniel")
                        .designation("Microservice Architect")
                        .salary(BigDecimal.valueOf(10000l))
                        .build());

        mvc.perform( MockMvcRequestBuilders
                .put("/api/employee/{id}", 1)
                .content(asJsonString(Employee.builder()
                        .id(1l)
                        .name("Daniel")
                        .designation("Microservice Architect")
                        .salary(BigDecimal.valueOf(10000l))
                        .build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Daniel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value("10000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.designation").value("Microservice Architect"));
    }

    @Test
    public void deleteEmployeeAPI() throws Exception {
        mvc.perform( MockMvcRequestBuilders.delete("/api/employee/{id}", 1) )
                .andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}