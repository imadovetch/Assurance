package com.Assurance.demo;

import com.Assurance.demo.AuthDep.domain.Services.UserService;
import com.Assurance.demo.AuthDep.infrastructure.Entities.App_User;
import com.Assurance.demo.AuthDep.infrastructure.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class AssuranceApplication implements CommandLineRunner  {

	@Autowired
	private UserService testRepositorie;

	public static void main(String[] args) {
		SpringApplication.run(AssuranceApplication.class, args);
	}

	public void run(String... args) throws Exception {
//		// Insert a new Test entity
//		Test testEntity = new Test();
//
//		testEntity.setName("Test");
////		testRepositorie.save(testEntity);

//		System.out.println(testRepositorie.findAll()); // Print all Test entities
		UserDTO x = new UserDTO("imad15", "imad@gmail.com", "060606060","060606060", "makmalkd", LocalDate.of(1990, 1, 1));
		testRepositorie.AddUser(x);



	}
}
