package com.example.demo.adminModel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class adminModelTest {

    @Test
    public void testNoArgsConstructor() {
        // Test du constructeur sans arguments
        adminModel admin = new adminModel();
        assertNotNull(admin);
    }

    @Test
    public void testAllArgsConstructor() {
        // Test du constructeur avec arguments
        adminModel admin = new adminModel("adminName", "adminPassword");
        assertEquals("adminName", admin.getAdminName());
        assertEquals("adminPassword", admin.getAdminPassword());
    }

    @Test
    public void testSetAndGetAdminID() {
        // Test des méthodes getter et setter pour adminID
        adminModel admin = new adminModel();
        admin.setAdminID(1L);
        assertEquals(1L, admin.getAdminID());
    }

    @Test
    public void testSetAndGetAdminName() {
        // Test des méthodes getter et setter pour adminName
        adminModel admin = new adminModel();
        admin.setAdminName("adminName");
        assertEquals("adminName", admin.getAdminName());
    }

    @Test
    public void testSetAndGetAdminPassword() {
        // Test des méthodes getter et setter pour adminPassword
        adminModel admin = new adminModel();
        admin.setAdminPassword("adminPassword");
        assertEquals("adminPassword", admin.getAdminPassword());
    }

    @Test
    public void testConstructorWithID() {
        // Test du constructeur avec ID
        adminModel admin = new adminModel(1L, "adminName", "adminPassword");
        assertEquals(1L, admin.getAdminID());
        assertEquals("adminName", admin.getAdminName());
        assertEquals("adminPassword", admin.getAdminPassword());
    }
}
