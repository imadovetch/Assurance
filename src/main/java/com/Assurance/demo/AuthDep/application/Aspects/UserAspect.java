package com.Assurance.demo.AuthDep.application.Aspects;

import com.Assurance.demo.AuthDep.application.mappers.UserMapper;
import com.Assurance.demo.AuthDep.infrastructure.Entities.App_User;
import com.Assurance.demo.AuthDep.infrastructure.dto.UserDTO;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@EnableAspectJAutoProxy
@Component
public class UserAspect {

    private final UserMapper userMapper;
    private static ThreadLocal<App_User> userThreadLocal = new ThreadLocal<>();

    @Autowired
    public UserAspect(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // This runs before AddUser is executed
    @Before("execution(* com.Assurance.demo.AuthDep.domain.Services.UserService.AddUser(..)) && args(userDTO,..)")
    public void MapDtoToEntity(UserDTO userDTO) {
        System.out.println("Aspect Starting");

        try {
            App_User user = userMapper.userDTOToUser(userDTO);
            userThreadLocal.set(user);
            System.out.println(user);
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred in UserAspect.MapDtoToEntity", e);
        }
    }


    @After("execution(* com.Assurance.demo.AuthDep.domain.Services.UserService.AddUser(..)) && args(userDTO,..)")
    public void clearThreadLocal() {
        System.out.println("Aspect: Clearing ThreadLocal after AddUser execution.");
        userThreadLocal.remove();
    }

    // This runs after an exception is thrown in AddUser
    @AfterThrowing(value = "execution(* com.Assurance.demo.AuthDep.domain.Services.UserService.AddUser(..))", throwing = "exception")
    public void handleException(Exception exception) {
        throw new RuntimeException("Exception occurred in UserAspect.handleException", exception);
//        System.out.println("Aspect: Exception caught in AddUser - " + exception.getMessage());
    }

    public static App_User getUserFromThreadLocal() {
        return userThreadLocal.get();
    }


}
