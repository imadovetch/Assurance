package com.Assurance.demo.AuthDep.domain.Services;

import com.Assurance.demo.AuthDep.application.Aspects.UserAspect;
import com.Assurance.demo.AuthDep.infrastructure.Entities.App_User;
import com.Assurance.demo.AuthDep.infrastructure.Entities.Role;
import com.Assurance.demo.AuthDep.infrastructure.Repositories.UseRepository;
import com.Assurance.demo.AuthDep.infrastructure.Repositories.RoleRepository;
import com.Assurance.demo.AuthDep.infrastructure.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UseRepository userRepository;

    @Autowired
    private RoleRepository roleRepository; // Inject RoleRepository to fetch roles

    public UserDTO AddUser(UserDTO userDTO) {
        App_User user = UserAspect.getUserFromThreadLocal();

        // Fetch the default role "USER" from the database
        Role userRole = roleRepository.findByName("USER");

        // Initialize roles if null and add the default role
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);

        // Save the user with the role
        userRepository.save(user);

        return userDTO;
    }

    public App_User findByName(String username) {
        return userRepository.findByName(username);
    }

    public List<App_User> findAll() {
        return userRepository.findAll();
    }
}
