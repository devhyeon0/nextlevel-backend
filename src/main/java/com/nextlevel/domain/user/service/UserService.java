package com.nextlevel.domain.user.service;

import com.nextlevel.domain.user.dto.UserResponseDto;
import com.nextlevel.domain.user.mapper.UserMapper;
import com.nextlevel.domain.user.dto.UserRequestDto;
import com.nextlevel.domain.user.entity.User;
import com.nextlevel.domain.user.repository.UserRepository;
import com.nextlevel.global.exception.ErrorCode;
import com.nextlevel.global.exception.ProfileApplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public void createUser(UserRequestDto userRequestDto) {
        userRepository.save(mapper.userRequestDtoToUser(userRequestDto));
    }

    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.USER_NOT_FOUND));
        user.update(userRequestDto);

        return mapper.userToUserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto findUser(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.USER_NOT_FOUND));

        return mapper.userToUserResponseDto(findUser);
    }

    public void deleteUser(Long userId) {
        User user = mapper.userResponseDtoToUser(findUser(userId));
        userRepository.delete(user);
    }

    private User verifyExistsByEmail(String email) {
        Optional<User> findUser = userRepository.findByEmail(email);

        return findUser.orElseThrow(() -> new ProfileApplicationException(ErrorCode.USER_NOT_FOUND));
    }
}
