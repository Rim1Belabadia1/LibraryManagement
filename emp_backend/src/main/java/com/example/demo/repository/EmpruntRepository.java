package com.example.demo.repository;

import com.example.demo.model.Emprunt;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
}
