package com.Assurance.demo.AuthDep.infrastructure.Entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
public class App_User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotBlank(message = "Name cannot be empty")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Phone number cannot be empty")
    @Column(nullable = false)
    private String phoneNumber;

    @NotBlank(message = "Address cannot be empty")
    @Column(nullable = false)
    private String adresse;

    @NotNull(message = "Age cannot be null")
    @Min(value = 0, message = "Age must be at least 0")
    private Integer age;
}