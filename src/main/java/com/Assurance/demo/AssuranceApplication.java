package com.Assurance.demo;

import com.Assurance.demo.AuthDep.domain.Services.UserService;
import com.Assurance.demo.AuthDep.infrastructure.Entities.App_User;
import com.Assurance.demo.AuthDep.infrastructure.Entities.Role;
import com.Assurance.demo.AuthDep.infrastructure.Repositories.RoleRepository;
import com.Assurance.demo.AuthDep.infrastructure.Repositories.UseRepository;
import com.Assurance.demo.AuthDep.infrastructure.dto.UserDTO;
import com.Assurance.demo.common.security.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;

@SpringBootApplication
public class AssuranceApplication implements CommandLineRunner {

	@Autowired
	private UserService testRepositorie;

	@Autowired
	private RoleRepository roleRepository; // Role repository for checking/adding roles

	@Autowired
	private UseRepository userRepository; // Repository to check/add users

	@Autowired
	private BCryptPasswordEncoder passwordEncoder; // To encrypt the password

	public static void main(String[] args) {
		SpringApplication.run(AssuranceApplication.class, args);
	}

	@Autowired
	public AuthRepository authRepository;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(authRepository.findAll());

		// Check if the 'USER' role exists, if not, create it
		if (roleRepository.findByName("USER") == null) {
			Role userRole = new Role();
			userRole.setName("USER");
			roleRepository.save(userRole);
		}

		// Check if the 'ADMIN' role exists, if not, create it
		if (roleRepository.findByName("ADMIN") == null) {
			Role adminRole = new Role();
			adminRole.setName("ADMIN");
			roleRepository.save(adminRole);
		}

		System.out.println("Roles 'USER' and 'ADMIN' are loaded into the database.");

		// Check if the 'admin' user exists, if not, create it
		if (userRepository.findByName("admin") == null) {
			// Fetch the 'ADMIN' role
			Role adminRole = roleRepository.findByName("ADMIN");

			// Create a new admin user
			App_User adminUser = new App_User();
			adminUser.setName("admin");
			adminUser.setEmail("admin@admin.com");
			adminUser.setPassword(passwordEncoder.encode("admin")); // Encrypt the password
			adminUser.setPhoneNumber("0600000000");
			adminUser.setAdresse("Admin Address");
			adminUser.setBirthDate(LocalDate.of(1980, 1, 1));

			// Assign the ADMIN role
			adminUser.setRoles(Collections.singleton(adminRole));

			// Save the admin user
			userRepository.save(adminUser);

			System.out.println("Admin user 'admin' has been created.");
		} else {
			System.out.println("Admin user already exists.");
		}
	}
}
