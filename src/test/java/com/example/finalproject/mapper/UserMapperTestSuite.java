package com.example.finalproject.mapper;

import com.example.finalproject.domain.User;
import com.example.finalproject.domain.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserMapperTestSuite {

    @Autowired
    private UserMapper userMapper;

        User user = new User("Pomiot", "Michal", "Rys", "asd", "asd", "afas@wp.pl", new HashSet<>(), new HashSet<>() );

    @Test
    void mapToUserDto() {
        //Given & When
        UserDto userDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals("Pomiot", userDto.getUsername());
    }

    @Test
    void mapToUser() {
        //Given
        UserDto userDto = userMapper.mapToUserDto(user);

        //When
        User result = userMapper.mapToUser(userDto);

        //Then
        assertNotEquals(result.getPassword(), userDto.getPassword());
    }

    @Test
    void mapToUserDtoList() {
        //Given
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);

        //When
        List<UserDto> userDtos = userMapper.mapToUserDtoList(users);

        //Then
        assertEquals(4, userDtos.size());
    }
}

