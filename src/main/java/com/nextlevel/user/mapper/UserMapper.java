package com.nextlevel.user.mapper;

import com.nextlevel.user.dto.UserRequestDto;
import com.nextlevel.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userRequestDtoToUser(UserRequestDto userRequestDto);
}
