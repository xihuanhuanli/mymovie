package com.cjj.myapplication.converter;


import com.cjj.myapplication.api.dto.UserDTO;
import com.cjj.myapplication.model.User;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;


@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper( UserConverter.class );

    User UserDTOToUser(UserDTO user);

    UserDTO UserToUserDTO(User user);

}
