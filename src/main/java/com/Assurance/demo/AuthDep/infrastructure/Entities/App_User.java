package com.Assurance.demo.AuthDep.infrastructure.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class App_User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @NotNull(message = "BirthDate cannot be null")
    @Past(message = "BirthDate must be a date in the past")
    @Column(nullable = true)
    private LocalDate birthDate;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;
}
