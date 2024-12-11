package com.Assurance.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssuranceApplication implements CommandLineRunner  {

//	@Autowired
//	private TestRepositorie testRepositorie;

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
	}
}
