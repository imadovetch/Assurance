package com.Assurance.demo.AuthDep.domain.Services;

import com.Assurance.demo.AuthDep.infrastructure.Entities.App_User;
import com.Assurance.demo.AuthDep.infrastructure.Repositories.UseRepository;
import com.Assurance.demo.AuthDep.infrastructure.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private  UseRepository useRepository;
    public  UserService(UseRepository useRepository){
        this.useRepository = useRepository;

    }

    public UserDTO AddUser(UserDTO Userdto){


        App_User user = new App_User();

        return  Userdto;
    }

}
