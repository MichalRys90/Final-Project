package com.example.finalproject.controller;

import com.example.finalproject.domain.User;
import com.example.finalproject.domain.dto.UserDto;
import com.example.finalproject.exception.UserDoesntExist;
import com.example.finalproject.exception.UserWithGivenUserNameExist;
import com.example.finalproject.mapper.UserMapper;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.TokenService;
import com.example.finalproject.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDbService userDbService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @GetMapping(value = "GetUsers")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userDbService.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    @PostMapping(value = "CreateUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) throws UserWithGivenUserNameExist {
        User user = userMapper.mapToUser(userDto);
        userDbService.saveUser(user);
        return ResponseEntity.ok(userMapper.mapToUserDto(user));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password) throws UserDoesntExist {
        User user = userRepository.findByUsername(username);
        String generateUserKey = tokenService.generateToken(username, password);
        user.setUserKey(generateUserKey);
        userRepository.save(user);
        return ResponseEntity.ok(generateUserKey);
    }

    @DeleteMapping(value = "removeUser/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.ok().build();
    }
}
