package com.Assurance.demo.AuthDep.application.Aspects;

import com.Assurance.demo.AuthDep.application.mappers.UserMapper;
import com.Assurance.demo.AuthDep.infrastructure.Entities.App_User;
import com.Assurance.demo.AuthDep.infrastructure.dto.UserDTO;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@EnableAspectJAutoProxy
@Component
public class UserAspect {

    private final UserMapper userMapper;

    @Autowired
    public UserAspect(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Before("execution(* com.Assurance.demo.AuthDep.domain.Services.UserService.AddUser(..)) && args(userDTO,..)")
    public void MapDtoToEntity(UserDTO userDTO) {
        App_User user = userMapper.userDTOToUser(userDTO);

    }
}
