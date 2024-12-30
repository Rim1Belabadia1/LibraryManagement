package com.example.demo.adminController;

import com.example.demo.adminModel.adminModel;
import com.example.demo.adminRepository.adminRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(adminController.class)
public class adminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private adminRepository repo;

    // Test pour récupérer tous les administrateurs
    @Test
    public void shouldReturnAllAdmins() throws Exception {
        // Mocking des données
        adminModel admin1 = new adminModel(1L, "admin1", "password1");
        adminModel admin2 = new adminModel(2L, "admin2", "password2");
        Mockito.when(repo.findAll()).thenReturn(Arrays.asList(admin1, admin2));

        // Effectuer une requête GET
        mockMvc.perform(get("/api/v1/admin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].adminName", is("admin1")))
                .andExpect(jsonPath("$[1].adminName", is("admin2")));
    }

    // Test pour le login avec succès
    @Test
    public void shouldLoginSuccessfully() throws Exception {
        // Mocking des données
        adminModel admin = new adminModel(1L, "admin1", "password1");
        Mockito.when(repo.findByAdminNameAndAdminPassword("admin1", "password1")).thenReturn(admin);

        // Effectuer une requête POST
        mockMvc.perform(post("/api/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"adminName\": \"admin1\", \"adminPassword\": \"password1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Login Successful"));
    }

    // Test pour le login avec échec
    @Test
    public void shouldFailLogin() throws Exception {
        // Mocking des données
        Mockito.when(repo.findByAdminNameAndAdminPassword("admin1", "wrongpassword")).thenReturn(null);

        // Effectuer une requête POST
        mockMvc.perform(post("/api/v1/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"adminName\": \"admin1\", \"adminPassword\": \"wrongpassword\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));
    }

    // Test pour l'inscription avec succès
    @Test
    public void shouldRegisterAdminSuccessfully() throws Exception {
        // Mocking des données
        adminModel newAdmin = new adminModel(null, "admin3", "password3");
        Mockito.when(repo.findByAdminName("admin3")).thenReturn(Optional.empty());

        // Effectuer une requête POST
        mockMvc.perform(post("/api/v1/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"adminName\": \"admin3\", \"adminPassword\": \"password3\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("Administrateur créé avec succès."));
    }

    // Test pour l'inscription échouée (administrateur existe déjà)
    @Test
    public void shouldFailToRegisterAdmin() throws Exception {
        // Mocking des données
        adminModel existingAdmin = new adminModel(1L, "admin1", "password1");
        Mockito.when(repo.findByAdminName("admin1")).thenReturn(Optional.of(existingAdmin));

        // Effectuer une requête POST
        mockMvc.perform(post("/api/v1/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"adminName\": \"admin1\", \"adminPassword\": \"password1\"}"))
                .andExpect(status().isConflict())
                .andExpect(content().string("L'administrateur existe déjà."));
    }
}
