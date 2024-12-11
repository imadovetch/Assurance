package com.Assurance.demo.AuthDep.infrastructure.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record UserDTO(

        @NotBlank(message = "Name cannot be empty")
        @Size(min = 5)
        String name,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "password cannot be empty")
        @Size(min = 8)
        String password,

        @NotBlank(message = "Phone number cannot be empty")
        String phoneNumber,

        @NotBlank(message = "Address cannot be empty")
        String adresse,

        @NotNull(message = "BirthDate cannot be null")
        @Past(message = "BirthDate must be a date in the past")
        LocalDate birthDate
) {
}
