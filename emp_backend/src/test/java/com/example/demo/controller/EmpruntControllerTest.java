package com.example.demo.controller;

import com.example.demo.model.Emprunt;
import com.example.demo.repository.EmpruntRepository;
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
public class EmpruntControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmpruntRepository empruntRepository;

    @InjectMocks
    private EmpruntController empruntController;

    private Emprunt emprunt;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(empruntController).build();
        emprunt = new Emprunt(1L, "Livre1", "Utilisateur1", "2024-01-01", "2024-01-15");
    }

    @Test
    void testAjouterEmprunt() throws Exception {
        when(empruntRepository.save(any(Emprunt.class))).thenReturn(emprunt);

        mockMvc.perform(post("/api/emprunts/emprunts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(emprunt)))
                .andExpect(status().isOk())
                .andExpect(content().string("Emprunt ajouté avec succès !"));
    }

    @Test
    void testGetAllEmprunts() throws Exception {
        when(empruntRepository.findAll()).thenReturn(Arrays.asList(emprunt));

        mockMvc.perform(get("/api/emprunts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].livre").value("Livre1"))
                .andExpect(jsonPath("$[0].utilisateur").value("Utilisateur1"));
    }

    @Test
    void testGetEmpruntById() throws Exception {
        when(empruntRepository.findById(1L)).thenReturn(Optional.of(emprunt));

        mockMvc.perform(get("/api/emprunts/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.livre").value("Livre1"))
                .andExpect(jsonPath("$.utilisateur").value("Utilisateur1"));
    }

    @Test
    void testGetEmpruntByIdNotFound() throws Exception {
        when(empruntRepository.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/emprunts/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateEmprunt() throws Exception {
        Emprunt updatedEmprunt = new Emprunt(1L, "Livre1", "Utilisateur1", "2024-01-01", "2024-02-01");
        when(empruntRepository.existsById(1L)).thenReturn(true);
        when(empruntRepository.save(any(Emprunt.class))).thenReturn(updatedEmprunt);

        mockMvc.perform(put("/api/emprunts/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedEmprunt)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dateRetour").value("2024-02-01"));
    }

    @Test
    void testUpdateEmpruntNotFound() throws Exception {
        Emprunt updatedEmprunt = new Emprunt(1L, "Livre1", "Utilisateur1", "2024-01-01", "2024-02-01");
        when(empruntRepository.existsById(1L)).thenReturn(false);

        mockMvc.perform(put("/api/emprunts/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedEmprunt)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteEmprunt() throws Exception {
        when(empruntRepository.existsById(1L)).thenReturn(true);
        doNothing().when(empruntRepository).deleteById(1L);

        mockMvc.perform(delete("/api/emprunts/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteEmpruntNotFound() throws Exception {
        when(empruntRepository.existsById(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/emprunts/{id}", 1L))
                .andExpect(status().isNotFound());
    }
}
