package com.example.demo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class EmployeeTest {

    private Employee employee;

    @BeforeEach
    void setUp() {
        // Initialiser l'objet Employee avant chaque test
        employee = new Employee("John", "Doe", "john.doe@example.com", 50000, "IT", "Developer", LocalDate.of(2020, 1, 1));
    }

    @Test
    void testGettersAndSetters() {
        // Vérification des getters et setters
        assertEquals("John", employee.getFname());
        assertEquals("Doe", employee.getLname());
        assertEquals("john.doe@example.com", employee.getEmail());
        assertEquals(50000, employee.getSalary());
        assertEquals("IT", employee.getDepartment());
        assertEquals("Developer", employee.getDesignation());
        assertEquals(LocalDate.of(2020, 1, 1), employee.getJoiningDate());
    }

    @Test
    void testConstructor() {
        // Tester le constructeur avec les paramètres
        LocalDate joiningDate = LocalDate.of(2021, 5, 10);
        Employee employeeFromConstructor = new Employee("Jane", "Smith", "jane.smith@example.com", 60000, "HR", "Manager", joiningDate);

        assertEquals("Jane", employeeFromConstructor.getFname());
        assertEquals("Smith", employeeFromConstructor.getLname());
        assertEquals("jane.smith@example.com", employeeFromConstructor.getEmail());
        assertEquals(60000, employeeFromConstructor.getSalary());
        assertEquals("HR", employeeFromConstructor.getDepartment());
        assertEquals("Manager", employeeFromConstructor.getDesignation());
        assertEquals(joiningDate, employeeFromConstructor.getJoiningDate());
    }

    @Test
    void testEmptyConstructor() {
        // Tester le constructeur vide
        Employee emptyEmployee = new Employee();

        assertNull(emptyEmployee.getFname());
        assertNull(emptyEmployee.getLname());
        assertNull(emptyEmployee.getEmail());
        assertEquals(0, emptyEmployee.getSalary());
        assertNull(emptyEmployee.getDepartment());
        assertNull(emptyEmployee.getDesignation());
        assertNull(emptyEmployee.getJoiningDate());
    }

    @Test
    void testSetters() {
        // Tester les setters
        employee.setFname("Michael");
        employee.setLname("Jordan");
        employee.setEmail("michael.jordan@example.com");
        employee.setSalary(70000);
        employee.setDepartment("Marketing");
        employee.setDesignation("Director");
        employee.setJoiningDate(LocalDate.of(2019, 3, 15));

        assertEquals("Michael", employee.getFname());
        assertEquals("Jordan", employee.getLname());
        assertEquals("michael.jordan@example.com", employee.getEmail());
        assertEquals(70000, employee.getSalary());
        assertEquals("Marketing", employee.getDepartment());
        assertEquals("Director", employee.getDesignation());
        assertEquals(LocalDate.of(2019, 3, 15), employee.getJoiningDate());
    }
}
