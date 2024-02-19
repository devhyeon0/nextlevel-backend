package com.nextlevel.user.mapper;

import com.nextlevel.user.dto.UserRequestDto;
import com.nextlevel.user.dto.UserResponseDto;
import com.nextlevel.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userRequestDtoToUser(UserRequestDto userRequestDto);

    @Mapping(source = "id", target = "userId")
    UserResponseDto userToUserResponseDto(User user);

    @Mapping(source = "userId", target = "id")
    User userResponseDtoToUser(UserResponseDto userResponseDto);
}
