package com.example.demo.adminRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.adminModel.adminModel;

import java.util.Optional;

@Repository
public interface adminRepository extends JpaRepository<adminModel, Long> {
    Optional<adminModel> findByAdminName(String adminName);

        adminModel findByAdminNameAndAdminPassword(String adminName, String adminPassword);
    }


