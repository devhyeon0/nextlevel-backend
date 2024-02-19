package com.nextlevel.user.controller;

import com.nextlevel.user.dto.UserRequestDto;
import com.nextlevel.user.dto.UserResponseDto;
import com.nextlevel.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Objects> postUser(@RequestBody UserRequestDto userRequestDto) {
        userService.createUser(userRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> patchUser(@PathVariable("id") Long userId,
                                                     @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.updateUser(userId, userRequestDto);

        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable("id") Long userId) {
        UserResponseDto userResponseDto = userService.findUser(userId);

        return ResponseEntity.ok(userResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Objects> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
