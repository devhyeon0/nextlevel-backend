package com.nextlevel.user.service;

import com.nextlevel.user.dto.UserRequestDto;
import com.nextlevel.user.mapper.UserMapper;
import com.nextlevel.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public void createUser(UserRequestDto userRequestDto) {
        userRepository.save(mapper.userRequestDtoToUser(userRequestDto));
    }
}
