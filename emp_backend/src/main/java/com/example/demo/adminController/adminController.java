package com.example.demo.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.adminModel.adminModel;
import com.example.demo.adminRepository.adminRepository;


@RestController
@RequestMapping("/api/v1/")
public class adminController {
	
	
	@Autowired
	private adminRepository repo;

	
	//get all 
	
	@GetMapping("/admin")
	public List <adminModel> getAllAdmins(){
		return repo.findAll();
	}
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody adminModel admin) {
		adminModel foundAdmin = repo.findByAdminNameAndAdminPassword(admin.getAdminName(), admin.getAdminPassword());
		if (foundAdmin != null) {
			return ResponseEntity.ok("Login Successful");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}
	@PostMapping("/signup")
	public ResponseEntity<String> registerAdmin(@RequestBody adminModel newAdmin) {
		if (repo.findByAdminName(newAdmin.getAdminName()).isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("L'administrateur existe déjà.");
		}
		repo.save(newAdmin);
		return ResponseEntity.status(HttpStatus.CREATED).body("Administrateur créé avec succès.");
	}


}
