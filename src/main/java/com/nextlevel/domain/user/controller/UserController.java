package com.nextlevel.domain.user.controller;

import com.nextlevel.domain.user.dto.UserResponseDto;
import com.nextlevel.domain.user.service.UserService;
import com.nextlevel.domain.user.dto.UserRequestDto;
import com.nextlevel.global.dto.SingleResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Objects> postUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        userService.createUser(userRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SingleResponseDto> patchUser(@PathVariable("id") Long userId,
                                                     @Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.updateUser(userId, userRequestDto);

        return ResponseEntity.ok(new SingleResponseDto<>(userResponseDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleResponseDto> getUser(@PathVariable("id") Long userId) {
        UserResponseDto userResponseDto = userService.findUser(userId);

        return ResponseEntity.ok(new SingleResponseDto<>(userResponseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Objects> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
