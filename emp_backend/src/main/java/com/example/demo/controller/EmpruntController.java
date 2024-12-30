package com.example.demo.controller;

import com.example.demo.model.Emprunt;
import com.example.demo.repository.EmpruntRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emprunts")
public class EmpruntController {

    private final EmpruntRepository empruntRepository;

    public EmpruntController(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }
    @PostMapping("/emprunts")
    public ResponseEntity<?> ajouterEmprunt(@RequestBody Emprunt emprunt) {
        System.out.println("Données reçues : " + emprunt);
        empruntRepository.save(emprunt);
        return ResponseEntity.ok("Emprunt ajouté avec succès !");
    }

    // Récupérer tous les emprunts
    @GetMapping
    public List<Emprunt> getAllEmprunts() {
        return empruntRepository.findAll();
    }

    // Récupérer un emprunt par son ID
    @GetMapping("/{id}")
    public Optional<Emprunt> getEmpruntById(@PathVariable Long id) {
        return empruntRepository.findById(id);
    }

    // Créer un nouvel emprunt
    @PostMapping
    public Emprunt createEmprunt(@RequestBody Emprunt emprunt) {
        return empruntRepository.save(emprunt);
    }

    // Mettre à jour un emprunt existant
    @PutMapping("/{id}")
    public Emprunt updateEmprunt(@PathVariable Long id, @RequestBody Emprunt emprunt) {
        if (empruntRepository.existsById(id)) {
            emprunt.setId(id);
            return empruntRepository.save(emprunt);
        }
        return null;
    }

    // Supprimer un emprunt
    @DeleteMapping("/{id}")
    public void deleteEmprunt(@PathVariable Long id) {
        empruntRepository.deleteById(id);
    }
}
