package com.example.finalproject.service;

import com.example.finalproject.domain.User;
import com.example.finalproject.exception.UserWithGivenUserNameExist;
import com.example.finalproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDbService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) throws UserWithGivenUserNameExist {
        User user1 = userRepository.findByUsername(user.getUsername());

        if (user1 == null) {
            return userRepository.save(user);
        }
        throw new UserWithGivenUserNameExist();
    }
}
