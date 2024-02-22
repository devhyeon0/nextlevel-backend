package com.nextlevel.user.service;

import com.nextlevel.user.dto.UserRequestDto;
import com.nextlevel.user.dto.UserResponseDto;
import com.nextlevel.user.entity.User;
import com.nextlevel.user.mapper.UserMapper;
import com.nextlevel.user.repository.UserRepository;
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
                .orElseThrow(() -> new IllegalArgumentException("계정이 존재하지 않습니다."));
        user.update(userRequestDto);

        return mapper.userToUserResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto findUser(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("계정이 존재하지 않습니다."));

        return mapper.userToUserResponseDto(findUser);
    }

    public void deleteUser(Long userId) {
        User user = mapper.userResponseDtoToUser(findUser(userId));
        userRepository.delete(user);
    }

    private User verifyExistsByEmail(String email) {
        Optional<User> findUser = userRepository.findByEmail(email);

        return findUser.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));
    }
}
