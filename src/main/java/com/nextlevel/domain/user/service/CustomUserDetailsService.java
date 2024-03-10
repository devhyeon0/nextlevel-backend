package com.nextlevel.domain.user.service;

import com.nextlevel.domain.user.dto.SecurityUserDetailsDto;
import com.nextlevel.domain.user.dto.UserDto;
import com.nextlevel.domain.user.entity.User;
import com.nextlevel.domain.user.mapper.UserMapper;
import com.nextlevel.domain.user.repository.UserRepository;
import com.nextlevel.global.exception.ErrorCode;
import com.nextlevel.global.exception.ProfileApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ProfileApplicationException(ErrorCode.USER_NOT_FOUND));

        UserDto userDto = mapper.userToUserDto(user);

        return new SecurityUserDetailsDto(userDto,
                Collections.singleton(new SimpleGrantedAuthority(userDto.userRole().toString())));
    }
}
