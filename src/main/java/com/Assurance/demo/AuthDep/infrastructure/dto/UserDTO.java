package com.Assurance.demo.AuthDep.infrastructure.dto;

import jakarta.validation.constraints.*;

public record UserDTO(

        @NotBlank(message = "Name cannot be empty")
        @Size(min = 5)
        String name,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Phone number cannot be empty")
        String phoneNumber,

        @NotBlank(message = "Address cannot be empty")
        String adresse,

        @NotNull(message = "Age cannot be null")
        @Min(value = 0, message = "Age must be at least 0")
        Integer age
) {
}
