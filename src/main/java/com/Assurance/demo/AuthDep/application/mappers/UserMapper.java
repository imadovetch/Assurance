package com.Assurance.demo.AuthDep.application.mappers;

import com.Assurance.demo.AuthDep.infrastructure.Entities.App_User;
import com.Assurance.demo.AuthDep.infrastructure.dto.UserDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(App_User user) ;
    App_User userDTOToUser(UserDTO userDTO);

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
