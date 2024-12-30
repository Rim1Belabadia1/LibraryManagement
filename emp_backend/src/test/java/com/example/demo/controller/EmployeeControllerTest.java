package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeController employeeController;

    private Employee employee;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        employee = new Employee(1L, "John", "Doe", "john.doe@example.com", "IT", "Developer", "2020-01-01", 60000);
    }

    @Test
    void testGetAllEmployees() throws Exception {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(employee));

        mockMvc.perform(get("/api/v1/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fname").value("John"))
                .andExpect(jsonPath("$[0].lname").value("Doe"));
    }

    @Test
    void testCreateEmployee() throws Exception {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        mockMvc.perform(post("/api/v1/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(employee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fname").value("John"))
                .andExpect(jsonPath("$.lname").value("Doe"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        mockMvc.perform(get("/api/v1/employees/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fname").value("John"))
                .andExpect(jsonPath("$.lname").value("Doe"));
    }

    @Test
    void testGetEmployeeByIdNotFound() throws Exception {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/employees/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Employee updatedEmployee = new Employee(1L, "John", "Doe", "john.doe@example.com", "IT", "Senior Developer", "2020-01-01", 80000);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        mockMvc.perform(put("/api/v1/employees/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedEmployee)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fname").value("John"))
                .andExpect(jsonPath("$.designation").value("Senior Developer"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).delete(employee);

        mockMvc.perform(delete("/api/v1/employees/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Deleted").value(true));
    }

    @Test
    void testLoginEmployee() throws Exception {
        when(employeeRepository.existsByFnameAndLname("John", "Doe")).thenReturn(true);

        mockMvc.perform(post("/api/v1/employees/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fname\": \"John\", \"lname\": \"Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(true));
    }

    @Test
    void testLoginEmployeeNotFound() throws Exception {
        when(employeeRepository.existsByFnameAndLname("John", "Doe")).thenReturn(false);

        mockMvc.perform(post("/api/v1/employees/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"fname\": \"John\", \"lname\": \"Doe\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(false));
    }
}
