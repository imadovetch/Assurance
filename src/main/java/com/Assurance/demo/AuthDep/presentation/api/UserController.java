package com.Assurance.demo.AuthDep.presentation.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/showuser")
    public String showUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            // Get the principal (user details) from the Authentication object
            Object principal = authentication.getPrincipal();

            // Check if the principal is an instance of User (the authenticated user details)
            if (principal instanceof User) {
                User user = (User) principal;
                // Print the username to the console
                System.out.println("Authenticated username: " + user.getUsername());

                // Return the username as a response
                return "User: " + user.getUsername();
            }
        }

        return "No user found in session";
    }
}
