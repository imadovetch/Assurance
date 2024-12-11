package com.Assurance.demo.AuthDep.domain.Services;

import com.Assurance.demo.AuthDep.application.Aspects.UserAspect;
import com.Assurance.demo.AuthDep.infrastructure.Entities.App_User;
import com.Assurance.demo.AuthDep.infrastructure.Repositories.UseRepository;
import com.Assurance.demo.AuthDep.infrastructure.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UseRepository userRepository;

    @Autowired
    public UserService(UseRepository useRepository) {
        this.userRepository = useRepository;
    }

    public UserDTO AddUser(UserDTO userDTO) {
        App_User user = UserAspect.getUserFromThreadLocal();

        userRepository.save(user);

        return userDTO;
    }


}
