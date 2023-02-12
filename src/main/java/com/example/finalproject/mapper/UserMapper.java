package com.example.finalproject.mapper;

import com.example.finalproject.domain.User;
import com.example.finalproject.domain.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    @Autowired
    private GuardianMapper guardianMapper;
    @Autowired
    private PatientMapper patientMapper;

    public User mapToUser(final UserDto userDto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        return new User(
                userDto.getUsername(),
                userDto.getFirstname(),
                userDto.getLastname(),
                "",
                bCryptPasswordEncoder.encode(userDto.getPassword()),
                userDto.getMail(),
                new HashSet<>(),
                new HashSet<>());
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(user.getUserId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getUserKey(),
                user.getPassword(),
                user.getMail(),
                guardianMapper.mapToGuardianDtoSet(user.getGuardians()),
                patientMapper.mapToPatientDtoSet(user.getPatients()));
    }

    public List<UserDto> mapToUserDtoList(final List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }
}
